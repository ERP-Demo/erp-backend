package cn.shiying.activiti.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.token.JwtUser;
import cn.shiying.common.enums.ErrorEnum;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ActivitiController {

    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @GetMapping("/startDrug")
    public Result startDrug(){
        return Result.ok().put("processInstanceId",strat("drug",null));
    }

    @PostMapping("/startPatient")
    public Result startPatient(Integer departmentId){
        Map<String , Object> variables = new HashMap<String,Object>();
        variables.put("departmentId",departmentId);
        return Result.ok().put("processInstanceId",strat("patient",variables));
    }

    @GetMapping("/registerPatient")
    public Result registerPatient(){
        JwtUser user=getUser();
        List<Task> list = taskService.createTaskQuery().list();
        List<Integer> deptWaitList=new ArrayList<>();
        List<Integer> personalDuringList=new ArrayList<>();
        List<Integer> personalWaitList=new ArrayList<>();
        for (Task task : list) {
            Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
            //判断有没有权限
            if (!user.getDepartmentId().contains(variables.get("departmentId"))) continue;

            if ("挂号".equals(task.getName())
                    &&variables.get("waitAssignee")!=null
                    &&user.getUid()==(Integer) variables.get("waitAssignee")) {
                personalWaitList.add((Integer) variables.get("registerId"));
            }else if ("挂号".equals(task.getName())){
                deptWaitList.add((Integer) variables.get("registerId"));
            }else if (variables.get("uid")!=null
                    && user.getUid()==(Integer) variables.get("uid")){
                personalDuringList.add((Integer) variables.get("registerId"));
            }
        }



        return Result.ok().put("deptWaitList",deptWaitList)
                .put("personalWaitList",personalWaitList)
                .put("personalDuringList",personalDuringList);
    }

    @PostMapping("/sysconsultation")
    public Result sysconsultation(String processInstanceId,Integer uid){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setVariable(task.getId(),"waitAssignee",uid);
        return Result.ok();
    }

    @PostMapping("/consultation")
    public Result consultation(String processInstanceId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setVariable(task.getId(),"waitAssignee",getUser().getUid());
        return Result.ok();
    }

    @PostMapping("/unconsultation")
    public Result unconsultation(String processInstanceId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.removeVariable(task.getId(),"waitAssignee");
        return Result.ok();
    }

    @PostMapping("/registerId")
    public Result registerId(String processInstanceId,Integer registerId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setVariable(task.getId(),"registerId",registerId);
        return Result.ok();
    }

    @PostMapping("/startDiagnosis")
    public Result startDiagnosis(String processInstanceId){
        JwtUser user=getUser();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Map<String, Object> variables = taskService.getVariables(task.getId());
        if (!user.getDepartmentId().contains(variables.get("departmentId"))||user.getUid()!=(Integer)variables.get("waitAssignee")) return Result.error(ErrorEnum.NO_AUTH);
        if (!"挂号".equals(task.getName())) return Result.error("已经在诊断了，请问重复发送");

        taskService.setVariable(task.getId(),"check",0);
        taskService.complete(task.getId());
        task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setVariable(task.getId(),"uid",user.getUid());
        taskService.setAssignee(task.getId(),user.getUsername());
        return Result.ok();
    }

    @PostMapping("/back")
    public Result back(String processInstanceId){
        JwtUser user=getUser();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        if (!"挂号".equals(task.getName())) return Result.error("已经在诊断了不能退号");
        taskService.setVariable(task.getId(),"check",1);
        taskService.complete(task.getId());
        task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        task.setAssignee(user.getUsername());
        taskService.complete(task.getId());
        return Result.ok();
    }

    @PostMapping("/bpmName")
    public Result bpmName(String processInstanceId){
        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if (list.size()==0)return Result.ok().put("bpmName","诊断完成");
        for (Task task : list) {
            return Result.ok().put("bpmName",task.getName());
        }
        return Result.ok();
    }


    private String strat(String bpmName,Map<String,Object> variables){
        JwtUser user=getUser();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(bpmName);
        String processInstanceId=processInstance.getProcessInstanceId();
        Task task=taskService.createTaskQuery()
                .processDefinitionKey(bpmName)
                .processInstanceId(processInstanceId).singleResult();
        taskService.setAssignee(task.getId(),user.getUsername());
        taskService.setVariables(task.getId(),variables);
        return processInstanceId;
    }

    private void dotask(String processInstanceId){
        dotask(processInstanceId, null);
    }

    private void dotask(String processInstanceId,int check) {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("check",check);
        dotask(processInstanceId,map);
    }

    private void dotask(String processInstanceId,Map<String,Object> map){
        JwtUser user=getUser();
        if (map==null){
            map=new HashMap<String,Object>();
            System.out.println("空");
        }
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.setAssignee(task.getId(),user.getUsername());
        map.put("uid",user.getUid());
        taskService.setVariables(task.getId(),map);
        taskService.complete(task.getId());
    }

    public JwtUser getUser(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user=new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        user.setDepartmentId((List<Integer>) map.get("departmentId"));
        return user;
    }
}
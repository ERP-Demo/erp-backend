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

import java.util.*;

@RestController
@RequestMapping("/")
public class ActivitiController {

    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @PostMapping("/startDrug")
    public Result startDrug(String purchaseId){
        JwtUser user=getUser();
        Map<String , Object> variables = new HashMap<String,Object>();
        variables.put("purchaseId",purchaseId);
        return Result.ok().put("processInstanceId",strat("drug",variables));
    }

    @PostMapping("/startPatient")
    public Result startPatient(Integer departmentId,String registerId){
        Map<String , Object> variables = new HashMap<String,Object>();
        variables.put("departmentId",departmentId);
        variables.put("registerId",registerId);
        return Result.ok().put("processInstanceId",strat("patient",variables));
    }

    @GetMapping("/registerPatient")
    public Result registerPatient(){
        JwtUser user=getUser();
        List<Task> list = taskService.createTaskQuery().processDefinitionKey("patient").list();
        List<String> deptWaitList=new ArrayList<>();
        List<String> personalDuringList=new ArrayList<>();
        List<String> personalWaitList=new ArrayList<>();
        for (Task task : list) {
            Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
            //判断有没有权限
            if (!user.getDepartmentId().contains(variables.get("departmentId"))) continue;

            if ("挂号".equals(task.getName())
                    &&variables.get("waitAssignee")!=null
                    &&user.getUid()==(Integer) variables.get("waitAssignee")) {
                personalWaitList.add((String) variables.get("registerId"));
            }else if ("挂号".equals(task.getName())){
                deptWaitList.add((String) variables.get("registerId"));
            }else if (variables.get("uid")!=null
                    && user.getUid()==(Integer) variables.get("uid")){
                personalDuringList.add((String) variables.get("registerId"));
            }
        }



        return Result.ok().put("deptWaitList",deptWaitList)
                .put("personalWaitList",personalWaitList)
                .put("personalDuringList",personalDuringList);
    }

    @GetMapping("/order")
    public Result order(){
        JwtUser user=getUser();
        List<Task> list = taskService.createTaskQuery().taskAssignee(user.getUsername()).taskName("提交申请").list();
        return Result.ok().put("ids",orderId(list));
    }

    @GetMapping("/checkOrder")
    public Result checkOrder(){
        List<Task> list = taskService.createTaskQuery().taskName("经理审核").list();
        return Result.ok().put("ids",orderId(list));
    }

    @GetMapping("/warehouseCheck")
    public Result warehouseCheck(){
        List<Task> list = taskService.createTaskQuery().taskName("验收").list();
        return Result.ok().put("ids",orderId(list));
    }

    @GetMapping("/manageCheck")
    public Result manageCheck(){
        List<Task> list = taskService.createTaskQuery().taskName("仓库经理审核").list();
        return Result.ok().put("ids",orderId(list));
    }

    @PostMapping("/agree")
    public Result agree(@RequestBody List<String> processInstanceIds){
        JwtUser user = getUser();
        List<Task> list = taskService.createTaskQuery().processInstanceIdIn(processInstanceIds).list();
        for (Task task : list) {
            task.setAssignee(user.getUsername());
            taskService.setVariable(task.getId(),"check",0);
            taskService.complete(task.getId());
        }
        return Result.ok().put("ids",orderId(list));
    }

    @GetMapping("/reject")
    public Result reject(@RequestParam("processInstanceIds") List<String> processInstanceIds,@RequestParam("reason") String reason){
        JwtUser user = getUser();
        List<Task> list = taskService.createTaskQuery().processInstanceIdIn(processInstanceIds).list();
        Map<String,String> map=new HashMap<>();
        for (Task task : list) {
            task.setAssignee(user.getUsername());
            Map<String, Object> variables=new HashMap<>();
            variables.put("check",1);
            variables.put("reason",reason);
            taskService.setVariables(task.getId(),variables);
            taskService.complete(task.getId());
            variables = runtimeService.getVariables(task.getProcessInstanceId());
            Task task1 = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            taskService.setAssignee(task1.getId(),(String) variables.get("subName"));
            map.put((String) variables.get("purchaseId"),task1.getProcessInstanceId());
        }
        return Result.ok().put("map",map);
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

    @PostMapping("/startDiagnosis")
    public Result startDiagnosis(String processInstanceId){
        JwtUser user=getUser();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Map<String, Object> variables = taskService.getVariables(task.getId());
        if (!user.getDepartmentId().contains(variables.get("departmentId"))||user.getUid()!=(Integer)variables.get("waitAssignee")) return Result.error(ErrorEnum.NO_AUTH);
        if (!"挂号".equals(task.getName())) return Result.error("已经在诊断了，请勿重复发送");

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
    public Result bpmName(@RequestParam("processInstanceIds") List<String> processInstanceIds){
        List<Task> list = taskService.createTaskQuery().processInstanceIdIn(processInstanceIds).list();
        Map<String,String> map=new HashMap<>();
        for (Task task : list) {
            map.put(task.getProcessInstanceId(),task.getName());
        }
        return Result.ok().put("map",map);
    }

    @PostMapping("/sub")
    public Result sub(@RequestBody List<String> processInstanceIds){
        JwtUser user=getUser();
        List<Task> list = taskService.createTaskQuery().processInstanceIdIn(processInstanceIds).taskName("提交申请").taskAssignee(user.getUsername()).list();
        for (Task task : list) {
            taskService.setVariable(task.getId(),"subName",user.getUsername());
            taskService.complete(task.getId());
        }
        return Result.ok().put("ids",orderId(list));
    }

    @PostMapping("/reason")
    public Result reason(String processInstanceId){
        JwtUser user=getUser();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Map<String, Object> variables = runtimeService.getVariables(processInstanceId);
        return Result.ok().put("reason",variables.get("reason"));
    }

    @PostMapping("/checkAgree")
    public Result checkAgree(String processInstanceId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskName("验收").singleResult();
        JwtUser user = getUser();
        taskService.setAssignee(task.getId(),user.getUsername());
        taskService.complete(task.getId());
        return Result.ok();
    }
    @PostMapping("/rejHandleReturned")
    public Result rejHandleReturned(String processInstanceId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskName("仓库经理审核").singleResult();
        if (task!=null) {
            JwtUser user = getUser();
            taskService.setAssignee(task.getId(),user.getUsername());
            taskService.setVariable(task.getId(),"check",1);
            taskService.complete(task.getId());
        }
        return Result.ok();
    }

    @PostMapping("/agreHandleReturned")
    public Result agreHandleReturned(String processInstanceId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).taskName("仓库经理审核").singleResult();
        if (task!=null) {
            JwtUser user = getUser();
            taskService.setAssignee(task.getId(),user.getUsername());
            taskService.setVariable(task.getId(),"check",0);
            taskService.complete(task.getId());
        }
        return Result.ok();
    }

    private List<String> orderId(List<Task> list){
        List<String> ids=new ArrayList<>();
        for (Task task : list) {
            Map<String, Object> variables = runtimeService.getVariables(task.getProcessInstanceId());
            ids.add((String) variables.get("purchaseId"));
        }
        return ids;
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
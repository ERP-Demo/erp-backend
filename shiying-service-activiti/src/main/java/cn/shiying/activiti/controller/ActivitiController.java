package cn.shiying.activiti.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.token.JwtUser;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ActivitiController {

    @Autowired
    TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/startDrug")
    public Result startDrug(){
        return Result.ok().put("processInstanceId",strat("drug"));
    }

    @GetMapping("/startPatient")
    public Result startPatient(){
        return Result.ok().put("processInstanceId",strat("patient"));
    }

    @PreAuthorize("hasAnyRole('2')")
    public Result durgcheckOk(String processInstanceId){
        dotask(processInstanceId,0);
        return Result.ok();
    }

    @PreAuthorize("hasAnyRole('2')")
    public Result durgcheckNo(String processInstanceId){
        dotask(processInstanceId,1);
        return Result.ok();
    }

    @PreAuthorize("hasAnyRole('3')")
    public Result suppliercheckOk(String processInstanceId){
        dotask(processInstanceId,0);
        return Result.ok();
    }

    @PreAuthorize("hasAnyRole('3')")
    public Result suppliercheckNo(String processInstanceId){
        dotask(processInstanceId,1);
        return Result.ok();
    }

    @PostMapping("/patientcheckOk")
    @PreAuthorize("hasAnyRole('4')")
    public Result patientcheckOk(String processInstanceId){
        dotask(processInstanceId,0);
        return Result.ok();
    }

    @PostMapping("/patientcheckNo")
    @PreAuthorize("hasAnyRole('4')")
    public Result patientcheckNo(String processInstanceId){
        dotask(processInstanceId,1);
        return Result.ok();
    }

    @PostMapping("/payment")
    public Result payment(String processInstanceId){
        dotask(processInstanceId);
        return Result.ok();
    }

    @PostMapping("/drug")
    @PreAuthorize("hasAnyRole('5')")
    public Result drug(String processInstanceId){
        dotask(processInstanceId,0);
        return Result.ok();
    }

    @PostMapping("/gotest")
    @PreAuthorize("hasAnyRole('5')")
    public Result gotest(String processInstanceId){
        dotask(processInstanceId,1);
        return Result.ok();
    }


    private String strat(String bpmName){
        JwtUser user=getUser();
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(bpmName);
        String processInstanceId=processInstance.getProcessInstanceId();
        Task task=taskService.createTaskQuery()
                .processDefinitionKey(bpmName)
                .processInstanceId(processInstanceId).singleResult();
        taskService.setAssignee(task.getId(),user.getUsername());
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

    private void gettask(String processInstanceId){
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        JwtUser user=getUser();

    }

    private void dotask(String processInstanceId,Map<String,Object> map){
        JwtUser user=getUser();
        if (map==null){
            map=new HashMap<String,Object>();
            System.out.println("空");
        }
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        System.out.println(task.getId());
        taskService.setAssignee(task.getId(),user.getUsername());
        map.put("id",user.getUid());
        taskService.setVariables(task.getId(),map);
        taskService.complete(task.getId());
    }

    public JwtUser getUser(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user=new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        return user;
    }
}
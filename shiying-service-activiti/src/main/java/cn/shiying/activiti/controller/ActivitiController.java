package cn.shiying.activiti.controller;

import cn.shiying.common.dto.Result;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ActivitiController {

    @Autowired
    TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 查询流程定义
     */
//    @RequestMapping("/getProcess")
//    public void getProcess(){
//        //查询所有流程定义信息
//        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
//        System.out.println("当前流程定义的数量："+processDefinitionPage.getTotalItems());
//        //获取流程信息
//        for (ProcessDefinition processDefinition:processDefinitionPage.getContent()) {
//            System.out.println("流程定义信息"+processDefinition);
//        }
//    }
    @GetMapping("/test")
    public Result stratBuyDrug(){
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("drug");
        String processInstanceId=processInstance.getProcessInstanceId();
        System.out.println(processInstanceId);
        System.out.println("开启了流程实例");
        List<Task> list=taskService.createTaskQuery()
                .processDefinitionKey("drug")
                .processInstanceId(processInstanceId).list();
        for(Task task:list){
            System.out.println("任务id："+list.get(0).getId());
            System.out.println("任务名称："+list.get(0).getName());
            taskService.setAssignee(list.get(0).getId(),"admin");
            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("totalDay",totalDay);
            map.put("name","admin");
            taskService.complete(task.getId(),map);
            System.out.println("admin"+"-->完成任务："+task.getId()+"."+task.getName());
        }
        return Result.ok();
    }

//    @PreAuthorize("hasAnyRole('2')")
    @GetMapping("test1")
    public Result test1() {
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("admin").list();
        if (list.size() > 0) {
            String taskId = list.get(0).getId();
            System.out.println("任务id：" + list.get(0).getId());
            System.out.println("任务名称：" + list.get(0).getName());
            return Result.ok().put("taskid",taskId);
        }else {
            return Result.ok();
        }
    }
}
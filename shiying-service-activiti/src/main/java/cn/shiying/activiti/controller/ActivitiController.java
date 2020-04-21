package cn.shiying.activiti.controller;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class ActivitiController {
    @Resource
    private ProcessRuntime processRuntime;
    @Resource
    private TaskRuntime taskRuntime;

    /**
     * 查询流程定义
     */
    @RequestMapping("/getProcess")
    public void getProcess(){
        //查询所有流程定义信息
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        System.out.println("当前流程定义的数量："+processDefinitionPage.getTotalItems());
        //获取流程信息
        for (ProcessDefinition processDefinition:processDefinitionPage.getContent()) {
            System.out.println("流程定义信息"+processDefinition);
        }
    }
}
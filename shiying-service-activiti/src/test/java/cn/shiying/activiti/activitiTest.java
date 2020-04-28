package cn.shiying.activiti;

import cn.shiying.common.entity.token.JwtUser;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class activitiTest {

    @Autowired
    TaskService taskService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @Test
    public void test(){
        Map<String, Object> variables = runtimeService.getVariables("cba98ef2-877d-11ea-a03e-8cec4b16bc5c");
//        Map<String, Object> processVariables = processInstance.getProcessVariables();
        System.out.println(variables);
    }


    @Test
    public void start(){
        Map<String, Object> processVariables =new HashMap<>();
        processVariables.put("test","12312312");
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("patient",processVariables);
        System.out.println(processInstance.getProcessInstanceId());
    }

}

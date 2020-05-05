package cn.shiying.register.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/activiti")
@FeignClient("shiying-service-activiti")
public interface ActivitiClient {
    @PostMapping("/startPatient")
    Result startPatient(@RequestParam("departmentId") Integer departmentId,@RequestParam("registerId") String registerId);

    @PostMapping("/consultation")
    Result consultation(String processInstanceId);

    @GetMapping("/registerPatient")
    Result registerPatient();

    @PostMapping("/back")
    Result back(@RequestParam("processInstanceId") String processInstanceId);

    @PostMapping("/bpmName")
    Result bpmName(@RequestParam("processInstanceId") String processInstanceId);
}

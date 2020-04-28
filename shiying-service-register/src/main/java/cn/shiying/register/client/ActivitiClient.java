package cn.shiying.register.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("shiying-service-activiti")
public interface ActivitiClient {
    @PostMapping("/activiti/startPatient")
    Result startPatient(@RequestParam("departmentId") Integer departmentId);

    @PostMapping("/activiti/consultation")
    Result consultation(String processInstanceId);

    @GetMapping("/activiti/registerPatient")
    Result registerPatient();

    @PostMapping("/activiti/registerId")
    Result registerId(@RequestParam("processInstanceId") String processInstanceId,@RequestParam("registerId")  Integer registerId);
}

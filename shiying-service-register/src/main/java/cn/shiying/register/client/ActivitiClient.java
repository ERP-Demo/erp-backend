package cn.shiying.register.client;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.patient.PatientDetailed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("shiying-service-activiti")
public interface ActivitiClient {
    @GetMapping("/activiti/startPatient")
    Result startPatient();
}

package cn.shiying.register.client;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.patient.PatientDetailed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("shiying-service-patient")
public interface PatienClient {
    @PostMapping("/patient/patient/detailed/save")
    Result save(@RequestBody PatientDetailed pd);


    @GetMapping("/patient/patient/detailed/isExist/{phone}")
    Result isExist(@PathVariable("phone") String phone);
}

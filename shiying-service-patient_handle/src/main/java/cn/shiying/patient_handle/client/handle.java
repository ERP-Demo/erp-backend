package cn.shiying.patient_handle.client;

import cn.shiying.common.dto.Result;
import cn.shiying.common.mapper.PatientDetailedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/patient/detailed")
@FeignClient("shiying-service-patient")
public interface handle {
    @GetMapping("/apply/payment")
    public Result payment();
}

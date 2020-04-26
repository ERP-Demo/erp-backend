package cn.shiying.auth.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("shiying-service-users-department")
public interface  DepartmentClient {
    @GetMapping("/list/{uid}")
    public Result list(Integer uid);
}

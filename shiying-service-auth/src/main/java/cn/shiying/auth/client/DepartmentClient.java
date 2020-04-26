package cn.shiying.auth.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("shiying-service-users-department")
public interface  DepartmentClient {
    @GetMapping("/users_department/Department/list/{uid}")
    public Result list(@PathVariable Integer uid);
}

package cn.shiying.users_department.client;

import cn.shiying.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("shiying-service-department")
public interface DepartmentClient {
    @GetMapping("/department/Department/selectById/{did}")
    public Result selectById(@PathVariable Integer did);

    @GetMapping("/department/Department/getAll")
    public Result getAll();
}

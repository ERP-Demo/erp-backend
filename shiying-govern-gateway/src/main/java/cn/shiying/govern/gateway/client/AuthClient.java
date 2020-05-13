package cn.shiying.govern.gateway.client;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.sys.SysMenu;
import cn.shiying.common.entity.sys.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Administrator.
 */
@FeignClient("shiying-service-auth")
public interface AuthClient {
    //根据账号查询用户信息
    @GetMapping("/auth/reset")
    Result reset();
}

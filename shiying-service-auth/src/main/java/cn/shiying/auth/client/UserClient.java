package cn.shiying.auth.client;

import cn.shiying.common.entity.sys.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator.
 */
@FeignClient("shiying-service-ucenter")
public interface UserClient {
    //根据账号查询用户信息
    @GetMapping("/ucenter/user/{username}")
    public SysUser selectByUsername(@RequestParam("username") String username);
}

package cn.shiying.ucenter.client;

import cn.shiying.common.entity.token.JwtUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator.
 */
@FeignClient("shiying-service-auth")
public interface AuthClient {

    @GetMapping("/auth/jwtuser")
    JwtUser jwtuser();
}

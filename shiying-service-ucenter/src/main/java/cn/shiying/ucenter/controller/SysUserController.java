package cn.shiying.ucenter.controller;

import cn.shiying.common.entity.sys.SysUser;
import cn.shiying.ucenter.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class SysUserController {
    @Autowired
    SysUserService userService;

    @GetMapping("/user/{username}")
    public SysUser selectByUsername(String username) {
        return userService.selectByUsername(username);
    }
}

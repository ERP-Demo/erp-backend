package cn.shiying.users_department.controller;

import cn.shiying.users_department.entity.vo.UsersDepartmentVo;
import feign.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.users_department.entity.UsersDepartment;
import cn.shiying.users_department.service.UsersDepartmentService;
import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.validator.ValidatorUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tyb
 * @since 2020-04-23
 */
@RestController
@RequestMapping("UserDepartment")
public class UsersDepartmentController {
    @Autowired
    private UsersDepartmentService departmentService;
    @RequestMapping("/all/{uid}")
    public Result All(@PathVariable Long uid){
        System.out.println("11111111111111");
        List<UsersDepartmentVo> list=departmentService.All(uid);
        System.out.println(list);
        return Result.ok().put("list",list);
    }
    public Result delById(@PathVariable Long userId, @PathVariable Integer departmentId){
        return Result.ok();
    }
}

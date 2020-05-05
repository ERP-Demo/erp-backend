package cn.shiying.users_department.controller;


import cn.shiying.users_department.entity.UsersDepartment;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.users_department.service.UsersDepartmentService;
import cn.shiying.common.dto.Result;
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
@RequestMapping("Department")
public class UsersDepartmentController {
    @Autowired
    private UsersDepartmentService departmentService;
//    @Autowired
//    private DepartmentMapper mapper;

    @GetMapping("/all/{uid}")
    public Result All(@PathVariable Integer uid){
        List<UsersDepartment> list1=departmentService.All(uid);
        return Result.ok().put("list",list1);
    }
    @RequestMapping("/deletebyid/{id}/{userId}")
    public Result delById(@PathVariable Integer id,@PathVariable Long userId){
        departmentService.delById(id,userId);
        return Result.ok();
    }

    //添加
    @RequestMapping("/add/{uid}/{ids}")
    public Result add(@PathVariable Integer uid,@PathVariable Integer[] ids){
        departmentService.add(uid,ids);
        return Result.ok();
    }

    @GetMapping("/list/{uid}")
    public Result list(@PathVariable Integer uid){
        List<Integer> list=departmentService.all(uid);
        return Result.ok().put("list",list);
    }
    //哪个接收

}

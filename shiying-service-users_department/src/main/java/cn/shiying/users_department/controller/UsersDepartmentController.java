package cn.shiying.users_department.controller;

import cn.shiying.common.entity.department.Department;
import cn.shiying.common.mapper.DepartmentMapper;
import cn.shiying.users_department.client.DepartmentClient;
import cn.shiying.users_department.entity.UsersDepartment;
import cn.shiying.users_department.entity.vo.UsersDepartmentVo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping("/all/{uid}")
    public Result All(@PathVariable Long uid){
        List<UsersDepartment> list1=departmentService.All(uid);
//        Department list=null;
//        List<Department> list2=null;
//        for (UsersDepartment ud : list1) {
//             list=mapper.selectById(ud);
//            list2=Arrays.asList(list);
//        }
//        list2.add(Arrays.asList(list))

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
}

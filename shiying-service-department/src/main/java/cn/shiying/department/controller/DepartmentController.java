package cn.shiying.department.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.department.entity.Department;
import cn.shiying.department.service.DepartmentService;
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
 * @since 2020-04-17
 */
@RestController
@RequestMapping("Department")
public class DepartmentController {
    @Autowired
    private DepartmentService DepartmentService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('department:Department:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = DepartmentService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('department:Department:info')")
    public Result info(@PathVariable("id") String id){
       Department Department = DepartmentService.getById(id);

        return Result.ok().put("Department", Department);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('department:Department:save')")
    public Result save(@RequestBody Department Department){
        ValidatorUtils.validateEntity(Department);
        DepartmentService.save(Department);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('department:Department:update')")
    public Result update(@RequestBody Department Department){
        ValidatorUtils.validateEntity(Department);
        DepartmentService.updateById(Department);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('department:Department:delete')")
    public Result delete(@RequestBody String[] ids){
        DepartmentService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

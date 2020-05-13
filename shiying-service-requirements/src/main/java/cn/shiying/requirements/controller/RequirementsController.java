package cn.shiying.requirements.controller;

import cn.shiying.common.entity.token.JwtUser;
import cn.shiying.requirements.entity.Vo.Requirements_Vo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.service.RequirementsService;
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
 * @since 2020-05-02
 */
@RestController
@RequestMapping("requirements")
public class RequirementsController {
    @Autowired
    private RequirementsService requirementsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('requirements:requirements:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = requirementsService.queryPage(params);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('requirements:requirements:info')")
    public Result info(@PathVariable("id") String id){
       Requirements requirements = requirementsService.getById(id);

        return Result.ok().put("requirements", requirements);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('requirements:requirements:save')")
    public Result save(@RequestBody Requirements requirements){
        System.out.println("数据=-----------"+requirements);
        ValidatorUtils.validateEntity(requirements);
        requirements.setUid(getUser().getUid());
        requirementsService.save(requirements);
        return Result.ok();
    }
    public JwtUser getUser(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user=new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        user.setDepartmentId((List<Integer>) map.get("departmentId"));
        return user;
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('requirements:requirements:update')")
    public Result update(@RequestBody Requirements requirements){
        ValidatorUtils.validateEntity(requirements);
        requirementsService.updateById(requirements);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('requirements:requirements:delete')")
    public Result delete(@RequestBody String[] ids){
        requirementsService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
    @GetMapping("/updatestate/{id}")
    public Result updatestate(@PathVariable Integer id){
        System.out.println(id);
        requirementsService.updatestate(id);
        return Result.ok();
    }
    @GetMapping("/All")
    public Result All(){
        List<Requirements_Vo> all = requirementsService.All();
        for (Requirements_Vo requirements_vo : all) {
            System.out.println("数据"+requirements_vo);
        }
        return Result.ok().put("list",all);
    }
}

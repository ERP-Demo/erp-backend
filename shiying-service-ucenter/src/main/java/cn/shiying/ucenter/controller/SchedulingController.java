package cn.shiying.ucenter.controller;

import cn.shiying.common.entity.scheduling.Scheduling;
import cn.shiying.common.entity.scheduling.Vo.SysUserVo;
import cn.shiying.common.entity.sys.SysUser;
import cn.shiying.ucenter.service.SchedulingService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
 * @since 2020-04-27
 */
@RestController
@RequestMapping("scheduling")
public class SchedulingController {
    @Autowired
    private SchedulingService schedulingService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('scheduling:scheduling:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = schedulingService.queryPage(params);
        List<SysUserVo> sysUserVos = schedulingService.SysUserVo();
        return Result.ok().put("page", page).put("list",sysUserVos);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('scheduling:scheduling:info')")
    public Result info(@PathVariable("id") String id){
       Scheduling scheduling = schedulingService.getById(id);

        return Result.ok().put("scheduling", scheduling);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('scheduling:scheduling:save')")
    public Result save(@RequestBody Scheduling scheduling){
        ValidatorUtils.validateEntity(scheduling);
        schedulingService.save(scheduling);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('scheduling:scheduling:update')")
    public Result update(@RequestBody Scheduling scheduling){
        ValidatorUtils.validateEntity(scheduling);
        schedulingService.updateById(scheduling);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('scheduling:scheduling:delete')")
    public Result delete(@RequestBody String[] ids){
        schedulingService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
    @RequestMapping("/All")
    public Result all(){
        List<SysUser> sysUsers = schedulingService.sysUser();
        for (SysUser User : sysUsers) {
            System.out.println(User);
        }
        return Result.ok().put("list",sysUsers);
    }
}

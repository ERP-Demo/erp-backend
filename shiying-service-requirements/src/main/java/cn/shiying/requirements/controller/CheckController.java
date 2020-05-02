package cn.shiying.requirements.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.requirements.entity.Check;
import cn.shiying.requirements.service.CheckService;
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
@RequestMapping("check")
public class CheckController {
    @Autowired
    private CheckService checkService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('requirements:check:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = checkService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('requirements:check:info')")
    public Result info(@PathVariable("id") String id){
       Check check = checkService.getById(id);

        return Result.ok().put("check", check);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('requirements:check:save')")
    public Result save(@RequestBody Check check){
        ValidatorUtils.validateEntity(check);
        checkService.save(check);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('requirements:check:update')")
    public Result update(@RequestBody Check check){
        ValidatorUtils.validateEntity(check);
        checkService.updateById(check);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('requirements:check:delete')")
    public Result delete(@RequestBody String[] ids){
        checkService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

package cn.shiying.icd.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.icd.service.IcdService;
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
@RequestMapping("icd")
public class IcdController {
    @Autowired
    private IcdService icdService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('icd:icd:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = icdService.queryPage(params);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('icd:icd:info')")
    public Result info(@PathVariable("id") String id){
       Icd icd = icdService.getById(id);

        return Result.ok().put("icd", icd);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('icd:icd:save')")
    public Result save(@RequestBody Icd icd){
        ValidatorUtils.validateEntity(icd);
        icdService.save(icd);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('icd:icd:update')")
    public Result update(@RequestBody Icd icd){
        ValidatorUtils.validateEntity(icd);
        icdService.updateById(icd);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('icd:icd:delete')")
    public Result delete(@RequestBody String[] ids){
        icdService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

    @PostMapping("/icds")
    public Result icds(@RequestParam("ids") List<String> ids){
        List<Icd> icds = (List<Icd>) icdService.listByIds(ids);
        return Result.ok().put("icds",icds);
    }
}

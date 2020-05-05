package cn.shiying.drugs.controller;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.drugs.service.DrugsStorageReportsLossService;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.drugs.service.DrugsDetailedService;
import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.validator.ValidatorUtils;
import org.springframework.web.bind.annotation.RestController;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
@RestController
@RequestMapping("detailed")
public class DrugsDetailedController {
    @Autowired
    private DrugsDetailedService detailedService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('drugs:detailed:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = detailedService.queryPage(params);
        return Result.ok().put("page", page);
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('drugs:detailed:info')")
    public Result info(@PathVariable("id") String id){
       DrugsDetailed detailed = detailedService.getById(id);
        return Result.ok().put("detailed", detailed);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('drugs:detailed:save')")
    public Result save(@RequestBody DrugsDetailed detailed){
        ValidatorUtils.validateEntity(detailed);
        detailedService.save(detailed);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('drugs:detailed:update')")
    public Result update(@RequestBody DrugsDetailed detailed){
        ValidatorUtils.validateEntity(detailed);
        detailedService.updateById(detailed);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('drugs:detailed:delete')")
    public Result delete(@RequestBody String[] ids){
        detailedService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}

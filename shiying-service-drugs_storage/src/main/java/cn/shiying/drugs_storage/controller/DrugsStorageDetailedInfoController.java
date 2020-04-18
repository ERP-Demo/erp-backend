package cn.shiying.drugs_storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
import cn.shiying.drugs_storage.service.DrugsStorageDetailedInfoService;
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
 * @since 2020-04-18
 */
@RestController
@RequestMapping("storage")
public class DrugsStorageDetailedInfoController {
    @Autowired
    private DrugsStorageDetailedInfoService storageService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('drugs_storage:storage:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = storageService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('drugs_storage:storage:info')")
    public Result info(@PathVariable("id") String id){
       DrugsStorageDetailedInfo storage = storageService.getById(id);

        return Result.ok().put("storage", storage);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('drugs_storage:storage:save')")
    public Result save(@RequestBody DrugsStorageDetailedInfo storage){
        ValidatorUtils.validateEntity(storage);
        storageService.save(storage);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('drugs_storage:storage:update')")
    public Result update(@RequestBody DrugsStorageDetailedInfo storage){
        ValidatorUtils.validateEntity(storage);
        storageService.updateById(storage);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('drugs_storage:storage:delete')")
    public Result delete(@RequestBody String[] ids){
        storageService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

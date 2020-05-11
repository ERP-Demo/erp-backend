package cn.shiying.patient_handle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.patient_handle.entity.PatientHandle;
import cn.shiying.patient_handle.service.PatientHandleService;
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
 * @since 2020-05-11
 */
@RestController
@RequestMapping("handle")
public class PatientHandleController {
    @Autowired
    private PatientHandleService handleService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('patient_handle:handle:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = handleService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('patient_handle:handle:info')")
    public Result info(@PathVariable("id") String id){
       PatientHandle handle = handleService.getById(id);

        return Result.ok().put("handle", handle);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('patient_handle:handle:save')")
    public Result save(@RequestBody PatientHandle handle){
        ValidatorUtils.validateEntity(handle);
        handleService.save(handle);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('patient_handle:handle:update')")
    public Result update(@RequestBody PatientHandle handle){
        ValidatorUtils.validateEntity(handle);
        handleService.updateById(handle);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('patient_handle:handle:delete')")
    public Result delete(@RequestBody String[] ids){
        handleService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

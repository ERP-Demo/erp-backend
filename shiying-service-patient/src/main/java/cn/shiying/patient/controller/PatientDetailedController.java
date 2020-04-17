package cn.shiying.patient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.patient.entity.PatientDetailed;
import cn.shiying.patient.service.PatientDetailedService;
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
@RequestMapping("/patient/detailed")
public class PatientDetailedController {
    @Autowired
    private PatientDetailedService detailedService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('patient:detailed:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = detailedService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('patient:detailed:info')")
    public Result info(@PathVariable("id") String id){
       PatientDetailed detailed = detailedService.getById(id);

        return Result.ok().put("detailed", detailed);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('patient:detailed:save')")
    public Result save(@RequestBody PatientDetailed detailed){
        ValidatorUtils.validateEntity(detailed);
        detailedService.save(detailed);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('patient:detailed:update')")
    public Result update(@RequestBody PatientDetailed detailed){
        ValidatorUtils.validateEntity(detailed);
        detailedService.updateById(detailed);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('patient:detailed:delete')")
    public Result delete(@RequestBody String[] ids){
        detailedService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

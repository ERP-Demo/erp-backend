package cn.shiying.electronic_case_model.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.electronic_case_model.entity.ElectronicCaseModel;
import cn.shiying.electronic_case_model.service.ElectronicCaseModelService;
import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.validator.ValidatorUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 病历模版表 前端控制器
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
@RestController
@RequestMapping("case")
public class ElectronicCaseModelController {
    @Autowired
    private ElectronicCaseModelService caseService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('electronic_case_model:case:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = caseService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('electronic_case_model:case:info')")
    public Result info(@PathVariable("id") String id){
       ElectronicCaseModel case = caseService.getById(id);

        return Result.ok().put("case", case);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('electronic_case_model:case:save')")
    public Result save(@RequestBody ElectronicCaseModel case){
        ValidatorUtils.validateEntity(case);
        caseService.save(case);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('electronic_case_model:case:update')")
    public Result update(@RequestBody ElectronicCaseModel case){
        ValidatorUtils.validateEntity(case);
        caseService.updateById(case);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('electronic_case_model:case:delete')")
    public Result delete(@RequestBody String[] ids){
        caseService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

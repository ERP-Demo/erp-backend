package cn.shiying.dms_case_model.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.dms_case_model.entity.DmsCaseModel;
import cn.shiying.dms_case_model.service.DmsCaseModelService;
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
public class DmsCaseModelController {
    @Autowired
    private DmsCaseModelService caseService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('dms_case_model:case:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = caseService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('dms_case_model:case:info')")
    public Result info(@PathVariable("id") String id){
       DmsCaseModel case = caseService.getById(id);

        return Result.ok().put("case", case);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('dms_case_model:case:save')")
    public Result save(@RequestBody DmsCaseModel case){
        ValidatorUtils.validateEntity(case);
        caseService.save(case);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('dms_case_model:case:update')")
    public Result update(@RequestBody DmsCaseModel case){
        ValidatorUtils.validateEntity(case);
        caseService.updateById(case);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('dms_case_model:case:delete')")
    public Result delete(@RequestBody String[] ids){
        caseService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

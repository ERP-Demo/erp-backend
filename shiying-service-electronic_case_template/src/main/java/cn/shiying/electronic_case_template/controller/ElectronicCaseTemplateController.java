package cn.shiying.electronic_case_template.controller;

import cn.shiying.common.entity.token.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.electronic_case_template.entity.ElectronicCaseTemplate;
import cn.shiying.electronic_case_template.service.ElectronicCaseTemplateService;
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
 * @since 2020-05-20
 */
@RestController
@RequestMapping("case")
public class ElectronicCaseTemplateController {
    @Autowired
    private ElectronicCaseTemplateService caseService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('electronic_case_template:case:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = caseService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('electronic_case_template:case:info')")
    public Result info(@PathVariable("id") String id){
       ElectronicCaseTemplate cas = caseService.getById(id);
        return Result.ok().put("case", cas);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('electronic_case_template:case:save')")
    public Result save(@RequestBody ElectronicCaseTemplate cas){
        ValidatorUtils.validateEntity(cas);
        caseService.save(cas);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('electronic_case_template:case:update')")
    public Result update(@RequestBody ElectronicCaseTemplate cas){
        ValidatorUtils.validateEntity(cas);
        caseService.updateById(cas);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('electronic_case_template:case:delete')")
    public Result delete(@RequestBody String[] ids){
        caseService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

    @GetMapping("/allTemplate")
    public Result allTemplate(){
        return Result.ok().put("list",caseService.allTemplate());
    }
}

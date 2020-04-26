package cn.shiying.test_correlation.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.validator.ValidatorUtils;
import cn.shiying.test_correlation.entity.TestCorrelation;
import cn.shiying.test_correlation.service.TestCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("correlation")
public class TestCorrelationAffiliateController {
    @Autowired
    private TestCorrelationService correlationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('test_correlationaffiliate:correlation:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = correlationService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('test_correlationaffiliate:correlation:info')")
    public Result info(@PathVariable("id") String id){
        TestCorrelation correlation = correlationService.getById(id);

        return Result.ok().put("correlation", correlation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test_correlationaffiliate:correlation:save')")
    public Result save(@RequestBody TestCorrelation correlation){
        ValidatorUtils.validateEntity(correlation);
        correlationService.save(correlation);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('test_correlationaffiliate:correlation:update')")
    public Result update(@RequestBody TestCorrelation correlation){
        ValidatorUtils.validateEntity(correlation);
        correlationService.updateById(correlation);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('test_correlationaffiliate:correlation:delete')")
    public Result delete(@RequestBody String[] ids){
        correlationService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

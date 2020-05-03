package cn.shiying.test_correlation.controller;

import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.test_correlation.entity.vo.TestCorrelationVO;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.test_correlation.entity.TestCorrelation;
import cn.shiying.test_correlation.service.TestCorrelationService;
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
 * @since 2020-04-28
 */
@RestController
@RequestMapping("correlation")
public class TestCorrelationController {
    @Autowired
    private TestCorrelationService correlationService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('test_correlation:correlation:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = correlationService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('test_correlation:correlation:info')")
    public Result info(@PathVariable("id") String id){
       TestCorrelation correlation = correlationService.getById(id);

        return Result.ok().put("correlation", correlation);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test_correlation:correlation:save')")
    public Result save(@RequestBody TestCorrelation correlation){
        ValidatorUtils.validateEntity(correlation);
        correlationService.save(correlation);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('test_correlation:correlation:update')")
    public Result update(@RequestBody TestCorrelation correlation){
        ValidatorUtils.validateEntity(correlation);
        correlationService.updateById(correlation);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('test_correlation:correlation:delete')")
    public Result delete(@RequestBody String[] ids){
        correlationService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

}

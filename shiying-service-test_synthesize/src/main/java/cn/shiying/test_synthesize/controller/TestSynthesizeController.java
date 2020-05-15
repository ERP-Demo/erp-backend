package cn.shiying.test_synthesize.controller;

import cn.shiying.test_synthesize.entity.form.SynthesizeAndProjects;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.test_synthesize.entity.TestSynthesize;
import cn.shiying.test_synthesize.service.TestSynthesizeService;
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
 * @since 2020-04-23
 */
@RestController
@RequestMapping("synthesize")
public class TestSynthesizeController {
    @Autowired
    private TestSynthesizeService synthesizeService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('test_synthesize:synthesize:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = synthesizeService.queryPage(params);
        System.out.println(page);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('test_synthesize:synthesize:info')")
    public Result info(@PathVariable("id") String id){
       TestSynthesize synthesize = synthesizeService.getById(id);

        return Result.ok().put("synthesize", synthesize);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test_synthesize:synthesize:save')")
    public Result save(@RequestBody TestSynthesize synthesize){
        ValidatorUtils.validateEntity(synthesize);
        synthesizeService.save(synthesize);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('test_synthesize:synthesize:update')")
    public Result update(@RequestBody TestSynthesize synthesize){
        ValidatorUtils.validateEntity(synthesize);
        synthesizeService.updateById(synthesize);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('test_synthesize:synthesize:delete')")
    public Result delete(@RequestBody String[] ids){
        synthesizeService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    /**
     * 添加化验
     */
    @PostMapping("/addSynthesizeAndProjects")
    public Result addSynthesizeAndProjects(@RequestBody SynthesizeAndProjects synthesizeAndProjects){
        synthesizeService.addSynthesizeAndProjects(synthesizeAndProjects);
        return Result.ok();
    }
    @GetMapping("/selectByid/{ids}")
    public Result selectByid(@PathVariable Integer[] ids) {
        return Result.ok();
    }
}

package cn.shiying.test_model.controller;

import cn.shiying.test_model.entity.from.TestModelFrom;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.test_model.entity.TestModel;
import cn.shiying.test_model.service.TestModelService;
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
 * @since 2020-05-05
 */
@RestController
@RequestMapping("model")
public class TestModelController {
    @Autowired
    private TestModelService modelService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('test_model:model:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = modelService.queryPage(params);
        System.out.println(page);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('test_model:model:info')")
    public Result info(@PathVariable("id") String id){
       TestModel model = modelService.getById(id);
        return Result.ok().put("model", model);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test_model:model:save')")
    public Result save(@RequestBody TestModelFrom from){
        //验证工具
        ValidatorUtils.validateEntity(from);
        TestModel t=from.getTestModel();
        modelService.save(t);
        System.out.println(t.getTestModelId());
        from.setTestModelId(from.getTestModelId());
        modelService.add(from.getTestModelId(),from.getIds());
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('test_model:model:update')")
    public Result update(@RequestBody TestModel model){
        ValidatorUtils.validateEntity(model);
        modelService.updateById(model);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('test_model:model:delete')")
    public Result delete(@RequestBody String[] ids){
        modelService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }
}

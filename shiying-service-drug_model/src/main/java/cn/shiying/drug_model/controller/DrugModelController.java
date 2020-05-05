package cn.shiying.drug_model.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.drug_model.entity.DrugModel;
import cn.shiying.drug_model.service.DrugModelService;
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
 * @since 2020-05-01
 */
@RestController
@RequestMapping("model")
public class DrugModelController {
    @Autowired
    private DrugModelService modelService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('drug_model:model:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = modelService.queryPage(params);
        System.out.println(page);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('drug_model:model:info')")
    public Result info(@PathVariable("id") String id){
       DrugModel model = modelService.getById(id);

        return Result.ok().put("model", model);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('drug_model:model:save')")
    public Result save(@RequestBody DrugModel model){
        ValidatorUtils.validateEntity(model);
        //modelService.save(model);
        System.out.println(model);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('drug_model:model:update')")
    public Result update(@RequestBody DrugModel model){
        ValidatorUtils.validateEntity(model);
        modelService.updateById(model);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('drug_model:model:delete')")
    public Result delete(@RequestBody String[] ids){
        modelService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
}

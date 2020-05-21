package cn.shiying.drug_model.controller;

import cn.shiying.drug_model.entity.from.DrugModelFrom;
import cn.shiying.drug_model.entity.vo.DrugModelVo;
import cn.shiying.drug_model.service.DrugsDrugModelService;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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
    @Autowired
    private DrugsDrugModelService dmservice;

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
    public Result info(@PathVariable("id") Integer id){
        List<DrugModelVo> drugModelVos = modelService.selectById(id);
        System.out.println(drugModelVos);
        DrugModelVo dv=new DrugModelVo();
        double allprice=0.0;
        for (DrugModelVo drugs:drugModelVos){
            allprice+=drugs.getDrugsPrice();
        }
        String price=String.valueOf(allprice);
        DrugModel selectbyid = modelService.selectbyid(id);
        return Result.ok().put("list",drugModelVos).put("mode",selectbyid).put("price",price);
    }
    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('drug_model:model:save')")
    public Result save(@RequestBody DrugModelFrom from){
        ValidatorUtils.validateEntity(from);
        DrugModel m=from.getDrugModel();
        modelService.save(m);
        System.out.println(m.getDrugModelId());
        from.setDrugModelId(m.getDrugModelId());
        modelService.add(m.getDrugModelId(),from.getIds());
        return Result.ok();
    }
    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('drug_model:model:update')")
    public Result update(@RequestBody DrugModelFrom from){
        ValidatorUtils.validateEntity(from);
        System.out.println(from);
        DrugModel m=from.getDrugModel();
        System.out.println("m:"+m);
        System.out.println("id:"+m.getDrugModelId());
        modelService.updateById(m);
        modelService.delbyid(m.getDrugModelId());
        modelService.add(m.getDrugModelId(),from.getIds());
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('drug_model:model:delete')")
    public Result delete(@RequestBody String[] ids){
        modelService.removeByIds(Arrays.asList(ids));
        System.out.println(Arrays.asList(ids));
        modelService.del(Arrays.asList(ids));
        return Result.ok();
    }
    @RequestMapping("/byid/{id}")
    public Result byid(@PathVariable Integer id){
        DrugModel selectbyid = modelService.selectbyid(id);
        return Result.ok().put("mode",selectbyid);
    }
}

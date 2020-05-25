package cn.shiying.supplier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.supplier.service.SupplierDetailedService;
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
 * @since 2020-04-16
 */
@RestController
@RequestMapping("detailed")
public class SupplierDetailedController {
    @Autowired
    private SupplierDetailedService detailedService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('supplier:detailed:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = detailedService.queryPage(params);
        System.out.println(page);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('supplier:detailed:info')")
    public Result info(@PathVariable("id") String id){
       SupplierDetailed detailed = detailedService.getById(id);

        return Result.ok().put("detailed", detailed);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('supplier:detailed:save')")
    public Result save(@RequestBody SupplierDetailed detailed){
        ValidatorUtils.validateEntity(detailed);
        detailedService.save(detailed);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('supplier:detailed:update')")
    public Result update(@RequestBody SupplierDetailed detailed){
        ValidatorUtils.validateEntity(detailed);
        detailedService.updateById(detailed);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('supplier:detailed:delete')")
    public Result delete(@RequestBody String[] ids){
        detailedService.removeByIds(Arrays.asList(ids));
        for (String id : ids) {
            System.out.println(id);
        }
        return Result.ok();
    }
    //插入关系
    @RequestMapping("/add/{pid}/{ids}")
    public Result add(@PathVariable Integer pid,@PathVariable Integer[] ids){
        detailedService.insertDrigs_supplier(pid,ids);
        return Result.ok();
    }

}

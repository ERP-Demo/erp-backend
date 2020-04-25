package cn.shiying.drugs_purchase.controller;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import cn.shiying.drugs_purchase.service.DrugsPurchaseService;
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
 * @since 2020-04-22
 */
@RestController
@RequestMapping("purchase")
public class DrugsPurchaseController {
    @Autowired
    private DrugsPurchaseService purchaseService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:info')")
    public Result info(@PathVariable("id") String id){
       DrugsPurchase purchase = purchaseService.getById(id);

        return Result.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:save')")
    public Result save(@RequestBody DrugsPurchase purchase){
        ValidatorUtils.validateEntity(purchase);
        purchaseService.save(purchase);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:update')")
    public Result update(@RequestBody DrugsPurchase purchase){
        ValidatorUtils.validateEntity(purchase);
        purchaseService.updateById(purchase);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:delete')")
    public Result delete(@RequestBody String[] ids){
        purchaseService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

    @GetMapping("/all/{pid}")
    public Result All(@PathVariable("pid") String pid){
        List<DrugsPurchaseDetailedVO> all = purchaseService.selectBypid(pid);
        return Result.ok().put("all",all);
    }

    @GetMapping("/sname")
    public Result sname(){
        List<SupplierDetailed> sname = purchaseService.selectSname();
        return Result.ok().put("sname",sname);
    }

    @GetMapping("/dname")
    public Result dname(){
        List<DrugsDetailed> dname = purchaseService.selectDname();
        return Result.ok().put("dname",dname);
    }

    @PostMapping("/pur")
    public Result pur(@RequestBody DrugsPurchase purchase){
        int flag=purchaseService.insertPurchase(purchase);

        return Result.ok();
    }

    @PostMapping("/det")
    public Result det(@RequestBody DrugsPurchaseDetailed drugsPurchaseDetailed){
        int flag=purchaseService.insertDetailed(drugsPurchaseDetailed);

        return Result.ok();
    }
}

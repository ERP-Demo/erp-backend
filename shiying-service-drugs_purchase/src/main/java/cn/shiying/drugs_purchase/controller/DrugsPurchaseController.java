package cn.shiying.drugs_purchase.controller;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.drugs_purchase.client.ActivitiClient;
import cn.shiying.drugs_purchase.entity.form.CheckForm;
import cn.shiying.drugs_purchase.entity.form.DrugsAndDetailed;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ActivitiClient activitiClient;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params,1);
        return Result.ok().put("page", page);
    }

    @GetMapping("/historyOrder")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:history')")
    public Result historyOrder(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.historyOrder(params);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:info')")
    public Result info(@PathVariable("id") String id){
        DrugsPurchase purchase = purchaseService.getById(id);
        //根据单号查询详细表
        List<DrugsPurchaseDetailed> detailed = purchaseService.getByDrugsId(id);
        return Result.ok().put("purchase", purchase).put("detailed",detailed);
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
    public Result update(@RequestBody DrugsAndDetailed detailed){
        System.out.println("进入修改");
        System.out.println("修改数据："+detailed);

        //订单编号
        String purchaseId=detailed.getPurchaseId();

        //根据单号修改进货表
        purchaseService.updateDrugs(detailed);
        //根据单号删除进货详细表
        purchaseService.delDrugs(purchaseId);
        //再添加进货详细表
        purchaseService.addDrugs(detailed);


//        ValidatorUtils.validateEntity(purchase);
//        purchaseService.updateById(purchase);
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

    @GetMapping("/supplier")
    public Result supplier(){
        List<SupplierDetailed> supplier = purchaseService.selectSname();
        return Result.ok().put("supplier",supplier);
    }

    @GetMapping("/drugs")
    public Result drugs(){
        List<DrugsDetailed> drugs = purchaseService.selectDname();
        return Result.ok().put("drugs",drugs);
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

    /**
     * 药品进货
     */
    @PostMapping("/addSupplierAndDrugs")
    public Result addSupplierAndDrugs(@RequestBody DrugsAndDetailed detailed){
        purchaseService.addSupplierAndDrugs(detailed);
        return Result.ok();
    }

    @GetMapping("/checkList")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:check:list')")
    public Result checkList(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params,0);
        return Result.ok().put("page", page);
    }

    @GetMapping("/warehouseCheck")
    @PreAuthorize("hasAuthority('warehouse:check:list')")
    public Result warehouseCheck(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params,3);
        return Result.ok().put("page", page);
    }

    @PostMapping("/subOrder")
    public Result subOrder(@RequestBody String[] sub){
        Result result= activitiClient.sub(Arrays.asList(sub));
        DrugsPurchase drugsPurchase=new DrugsPurchase();
        drugsPurchase.setSubName(getUserName());
        order(result,drugsPurchase);
        return Result.ok();
    }

    @PostMapping("/agree")
    public Result agree(@RequestBody String[] ids){
        Result result= activitiClient.agree(Arrays.asList(ids));
        DrugsPurchase drugsPurchase=new DrugsPurchase();
        drugsPurchase.setCheckName(getUserName());
        order(result,drugsPurchase);
        return Result.ok();
    }

    @PostMapping("/reject")
    public Result reject(@RequestBody CheckForm form){
        Result result= activitiClient.reject(form.getIds(),form.getReason());
        DrugsPurchase drugsPurchase=new DrugsPurchase();
        drugsPurchase.setCheckName(getUserName());
        order(result,drugsPurchase);
        return Result.ok();
    }

    private void order(Result result,DrugsPurchase drugsPurchase){
        if ((Integer) result.get("code")!=200) ExceptionCast.cast(ErrorEnum.LOAD_TIME_LANG);
        List<String> ids =(List<String>) result.get("ids");
        if (ids.size()==0) ExceptionCast.cast(ErrorEnum.UNKNOWN);
        purchaseService.update(drugsPurchase,new UpdateWrapper<DrugsPurchase>().in("purchase_id",ids));
    }

    public String getUserName(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (String) map.get("username");
    }
}

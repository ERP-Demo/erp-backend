package cn.shiying.drugs_purchase.controller;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.drugs_purchase.client.ActivitiClient;
import cn.shiying.drugs_purchase.client.DrugsStorageClient;
import cn.shiying.drugs_purchase.entity.PurchaseReturned;
import cn.shiying.drugs_purchase.entity.PurchaseReturnedDetailed;
import cn.shiying.drugs_purchase.entity.form.CheckForm;
import cn.shiying.drugs_purchase.entity.form.DrugsAndDetailed;
import cn.shiying.drugs_purchase.entity.form.Returned;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import cn.shiying.drugs_purchase.entity.vo.ReturnedAndDetailedVO;
import cn.shiying.drugs_purchase.service.DrugsPurchaseDetailedService;
import cn.shiying.drugs_purchase.service.PurchaseReturnedDetailedService;
import cn.shiying.drugs_purchase.service.PurchaseReturnedService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.HashMap;
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
    private ActivitiClient activitiClient;

    @Autowired
    private DrugsStorageClient drugsStorageClient;

    @Autowired
    private DrugsPurchaseService purchaseService;

    @Autowired
    private PurchaseReturnedService purchaseReturnedService;

    @Autowired
    private PurchaseReturnedDetailedService detailedService;

    @Autowired
    private DrugsPurchaseDetailedService drugsPurchaseDetailedService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:list')")
    public Result list(@RequestParam Map<String, Object> params){
        Result result = activitiClient.order();
        PageUtils page = purchaseService.queryPage(params,result);
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
        List<PurchaseReturned> list = purchaseReturnedService.list(new QueryWrapper<PurchaseReturned>().and(i -> i.eq("purchase_id", pid).ne("status",2)));
        Map<Integer,Integer> map=new HashMap<>();
        Map<Integer,Integer> tuihuo=new HashMap<>();
        for (PurchaseReturned returned : list) {
            List<PurchaseReturnedDetailed> detaileds = detailedService.list(new QueryWrapper<PurchaseReturnedDetailed>().eq("tuihuo_id", returned.getTuihuoId()));
            for (PurchaseReturnedDetailed detailed : detaileds) {
                if (returned.getStatus()==1) {
                    if (tuihuo.get(detailed.getPdid()) == null) {
                        tuihuo.put(detailed.getPdid(), detailed.getPdNum());
                        continue;
                    }
                    tuihuo.put(detailed.getPdid(), map.get(detailed.getPdid()) + detailed.getPdNum());
                } else {
                    if (map.get(detailed.getPdid()) == null) {
                        map.put(detailed.getPdid(), detailed.getPdNum());
                        continue;
                    }
                    map.put(detailed.getPdid(), map.get(detailed.getPdid()) + detailed.getPdNum());
                }
            }
        }
        for (DrugsPurchaseDetailedVO vo : all) {
            vo.setTuihuoNum(tuihuo.get(vo.getPdid())==null?0:tuihuo.get(vo.getPdid()));
            vo.setFreezeNum(map.get(vo.getPdid())==null?0:map.get(vo.getPdid()));
        }
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

    /**
     * 药品退货
     */
    @PostMapping("/purchaseReturned")
    public Result purchaseReturned(@RequestBody Returned returned){
        purchaseService.purchaseReturned(returned);
        return Result.ok();
    }

    @GetMapping("/checkList")
    @PreAuthorize("hasAuthority('drugs_purchase:purchase:check:list')")
    public Result checkList(@RequestParam Map<String, Object> params){
        Result result = activitiClient.checkOrder();
        PageUtils page = purchaseService.queryPage(params,result);
        return Result.ok().put("page", page);
    }

    @GetMapping("/warehouseCheck")
    @PreAuthorize("hasAuthority('warehouse:check:list')")
    public Result warehouseCheck(@RequestParam Map<String, Object> params){
        Result result = activitiClient.warehouseCheck();
        PageUtils page = purchaseService.queryPage(params,result);
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

    @PostMapping("/agreeWarehouse")
    public Result agreeWarehouse(@RequestBody String[] ids){
        Result result= activitiClient.agree(Arrays.asList(ids));
        DrugsPurchase drugsPurchase=new DrugsPurchase();
        drugsPurchase.setWarehouseName(getUserName());
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

    @GetMapping("/allreturned")
    @PreAuthorize("hasAuthority('returned:check:list')")
    public Result allreturned(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.allreturned(params);
        return Result.ok().put("page", page);
    }

    //根据退货编号查询详细
    @GetMapping("/selectReturned/{tuihuoId}")
    public Result selectReturned(@PathVariable("tuihuoId") String tuihuoId){
        List<ReturnedAndDetailedVO> all = purchaseService.selectReturned(tuihuoId);
        return Result.ok().put("all",all);
    }

    @PostMapping("/rejHandleReturned")
    public Result rejHandleReturned(Integer tuihuoId){
        PurchaseReturned returned = purchaseReturnedService.getById(tuihuoId);
        if (returned.getStatus()!=0) return Result.error("已经审核过了，请勿重复提交");
        DrugsPurchase drugsPurchase = purchaseService.getById(returned.getPurchaseId());
        activitiClient.rejHandleReturned(drugsPurchase.getProcessInstanceId());
        PurchaseReturned purchaseReturned =new PurchaseReturned();
        purchaseReturned.setTuihuoId(tuihuoId);
        purchaseReturned.setStatus(2);
        purchaseReturnedService.updateById(purchaseReturned);
        return Result.ok();
    }

    @PostMapping("/agreHandleReturned")
    public Result agreHandleReturned(Integer tuihuoId){
        PurchaseReturned returned = purchaseReturnedService.getById(tuihuoId);
        if (returned.getStatus()!=0) return Result.error("已经审核过了，请勿重复提交");
        DrugsPurchase drugsPurchase = purchaseService.getById(returned.getPurchaseId());
        drugsPurchase.setWarehouseCheckName(getUserName());
        purchaseService.updateById(drugsPurchase);
        PurchaseReturned purchaseReturned =new PurchaseReturned();
        purchaseReturned.setTuihuoId(tuihuoId);
        purchaseReturned.setStatus(1);
        purchaseReturnedService.updateById(purchaseReturned);
        activitiClient.agreHandleReturned(drugsPurchase.getProcessInstanceId());
        return Result.ok();
    }

    @GetMapping("/manageCheck")
    public Result manageCheck(@RequestParam Map<String, Object> params){
        Result result = activitiClient.manageCheck();
        PageUtils page = purchaseService.queryPage(params,result.put("check",1));
        return Result.ok().put("page", page);
    }

    @PostMapping("/agreHandleManage")
    public Result agreHandleManage(String purchaseId){
        DrugsPurchase purchase=new DrugsPurchase();
        purchase.setPurchaseId(purchaseId);
        purchase.setWarehouseCheckName(getUserName());
        purchaseService.updateById(purchase);
        List<DrugsPurchaseDetailed> list = drugsPurchaseDetailedService.list(new QueryWrapper<DrugsPurchaseDetailed>().eq("purchase_id", purchaseId));
        List<PurchaseReturned> returneds = purchaseReturnedService.list(new QueryWrapper<PurchaseReturned>().eq("status", 1).eq("purchase_id", purchaseId));
        Map<Integer,Integer> map=new HashMap<>();
        for (PurchaseReturned returned : returneds) {
            List<PurchaseReturnedDetailed> tuihuo_id = detailedService.list(new QueryWrapper<PurchaseReturnedDetailed>().eq("tuihuo_id", returned.getTuihuoId()));
            for (PurchaseReturnedDetailed detailed : tuihuo_id) {
                Integer pdnum = map.get(detailed.getPdid());
                map.put(detailed.getPdid(),pdnum==null ? detailed.getPdNum():pdnum+detailed.getPdNum());
            }
        }
        for (DrugsPurchaseDetailed detailed : list) {
            Integer pdnum = map.get(detailed.getPdid());
            if (pdnum!=null)
                detailed.setPdNum(detailed.getPdNum()-pdnum);
        }
        Result save = drugsStorageClient.save(list);
        if ((Integer) save.get("code")!=200) return Result.error(ErrorEnum.UNKNOWN);
        Result result = activitiClient.agreHandleReturned(purchaseService.getById(purchaseId).getProcessInstanceId());
        if ((Integer) result.get("code")!=200) return Result.error(ErrorEnum.UNKNOWN);
        return Result.ok();
    }
}

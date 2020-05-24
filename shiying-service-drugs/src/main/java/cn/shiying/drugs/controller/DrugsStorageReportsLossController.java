package cn.shiying.drugs.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import cn.shiying.drugs.service.DrugsStorageReportsLossService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reports")
public class DrugsStorageReportsLossController {

    @Autowired
    private DrugsStorageReportsLossService storageService;

    /*药品的报损申请单*/
    /**
     * 查询药品表（下拉列表）
     */
    @GetMapping("/selectDrugsId")
    public Result selectDrugsId(){
        List<DrugsStorageReportsLoss> list=storageService.selectDrugsId();
        System.out.println("数据："+list);
        return Result.ok().put("list", list);
    }
    @GetMapping("/all")
    public Result all(){
        List<DrugsDetailed> list=storageService.all();
        //System.out.println("数据："+list);
        return  Result.ok().put("list", list);
    }
    @GetMapping("/selectByIds/{ids}")
    public Result selectByIds(@PathVariable Integer[] ids){
        List<DrugsDetailed> drugsDetaileds = storageService.queryByIds(ids);
        //System.out.println("1111111"+drugsDetaileds);
        System.out.println("1111111"+drugsDetaileds);
        return Result.ok().put("list",drugsDetaileds);
    }
    /**
     * 药品的报损添加
     */
    @PostMapping("/addStorageReport")
    public Result addStorageReport(@RequestBody DrugsStorageReportsLoss DrugsStorage){
        storageService.addStorageReport(DrugsStorage);
        return Result.ok();
    }

    @GetMapping("/StorageReportList")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = storageService.queryPage(params);
        return Result.ok().put("page", page);
    }

}

package cn.shiying.drugs.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import cn.shiying.drugs.service.DrugsStorageReportsLossService;
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
        return Result.ok().put("list", list);
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

package cn.shiying.drugs_purchase.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs_purchase.service.SupplierAndDrugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("SupplierAndDrugs")
public class SupplierAndDrugsController {

    @Autowired
    private SupplierAndDrugsService sdService;

    /**
     * 根据供应商查询对应的药品
     */
    @GetMapping("/selectSupplierIdByDrugs")
    public Result selectSupplierIdByDrugs(@RequestParam Map<String, Object> params){
        PageUtils page = sdService.queryPage(params);
        return Result.ok().put("page", page);
    }

}

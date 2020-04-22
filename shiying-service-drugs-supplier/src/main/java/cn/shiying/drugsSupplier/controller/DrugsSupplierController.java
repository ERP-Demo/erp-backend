package cn.shiying.drugsSupplier.controller;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugsSupplier.service.DrugsSupplierService;
import cn.shiying.drugsSupplier.entity.vo.DrugsSupplierVO;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/drugsp")
public class DrugsSupplierController {
    @Autowired
    private DrugsSupplierService drugsSupplierService;
    @RequestMapping("/all/{id}")
    public Result All(@PathVariable Integer id){
        List<DrugsSupplierVO> list = drugsSupplierService.All(id);
        return Result.ok().put("list",list);
    }
    @RequestMapping("/deletebyid/{id}/{did}")
    public Result deletebyid(@PathVariable Integer id,@PathVariable Integer did){
        drugsSupplierService.deletebyid(id,did);
        return Result.ok();
    }
}

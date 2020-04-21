package cn.shiying.drugsSupplier.controller;

import cn.shiying.drugsSupplier.service.DrugsSupplierService;
import cn.shiying.drugsSupplier.entity.vo.DrugsSupplierVO;
import org.springframework.web.bind.annotation.RequestMapping;
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
//请求不到这个你网关没配置
    @RequestMapping("/all")
    public Result All(){
        System.out.println("进入-------------------------");
        List<DrugsSupplierVO> all = drugsSupplierService.All(1);
        for (DrugsSupplierVO drugsSupplierVO : all) {
            System.out.println(drugsSupplierVO);
        }
        return Result.ok();
    }
    //这个模块好了？这个模块端口和供货商一样

}

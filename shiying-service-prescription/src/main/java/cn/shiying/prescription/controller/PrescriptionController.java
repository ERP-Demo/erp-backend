package cn.shiying.prescription.controller;

import cn.shiying.prescription.entity.PrescriptionDetails;
import cn.shiying.prescription.entity.from.DrugsAndDetailed;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.prescription.entity.Prescription;
import cn.shiying.prescription.service.PrescriptionService;
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
 * @since 2020-04-30
 */
@RestController
@RequestMapping("prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('prescription:prescription:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = prescriptionService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('prescription:prescription:info')")
    public Result info(@PathVariable("id") String id){
       Prescription prescription = prescriptionService.getById(id);

        return Result.ok().put("prescription", prescription);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('prescription:prescription:save')")
    public Result save(@RequestBody Prescription prescription){
        ValidatorUtils.validateEntity(prescription);
        prescriptionService.save(prescription);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('prescription:prescription:update')")
    public Result update(@RequestBody Prescription prescription){
        ValidatorUtils.validateEntity(prescription);
        prescriptionService.updateById(prescription);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('prescription:prescription:delete')")
    public Result delete(@RequestBody String[] ids){
        prescriptionService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
    @PostMapping("/addDrugsAndDetailed")
    public Result addDrugsAndDetailed(@RequestBody List<DrugsAndDetailed> drugsAndDetailed){
        return Result.ok().put("map",prescriptionService.addDrugsAndDetailed(drugsAndDetailed));
    }
    @PostMapping("/toVoid")
    public Result toVoid(@RequestBody List<Integer> ids){
        for (Integer id : ids) {
            System.out.println(id);
        }
        prescriptionService.toVoid(ids);
        return Result.ok();
    }
}
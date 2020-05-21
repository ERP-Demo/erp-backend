package cn.shiying.prescription.controller;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.prescription.entity.PrescriptionDetails;
import cn.shiying.prescription.entity.Prescription_Vo;
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
    @GetMapping("/byid/{id}")
    public Result byid(@PathVariable("id") Integer id){
        List<DrugsAndDetailed> list=prescriptionService.AllbyPid(id);
        System.out.println(list);
        return Result.ok().put("list",list);
    }

    @GetMapping("/bypdid/{id}")
    public Result bypdid(@PathVariable("id") Integer id){
        prescriptionService.bypdid(id);
        return Result.ok();
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
        prescriptionService.toVoid(ids);
        return Result.ok();
    }

    @GetMapping("/All")
    public Result All(@RequestParam Map<String, Object> params) {

        PageUtils page = prescriptionService.queryPagePre(params);

        return Result.ok().put("page", page);

        /*List<Prescription_Vo> prescription_vos = prescriptionService.PrescriptionVo();
        for (Prescription_Vo prescription_vo : prescription_vos) {
//            Integer patientId=prescription_vo.getPatientDetailed().getPatientId();
//            System.out.println("id:"+patientId);
            System.out.println("ALL数据："+prescription_vo);
        }
        return Result.ok().put("list", prescription_vos);*/
    }

    @GetMapping("/selectByid/{id}")
    public Result selectByid(@PathVariable Integer[] id) {
        List<Prescription_Vo> prescription_vos = prescriptionService.PrescriptionVoByid(id);
        return Result.ok().put("list", prescription_vos);
    }

    @GetMapping("/updatestate/{id}")
    public Result updatestate(@PathVariable Integer[] id) {
        prescriptionService.updatestate(id);
        return Result.ok();
    }
}

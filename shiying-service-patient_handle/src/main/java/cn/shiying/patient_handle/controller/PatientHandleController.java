package cn.shiying.patient_handle.controller;

import cn.shiying.common.entity.patient_handle.PatientHandle;
import cn.shiying.common.entity.patient_handle.PatientHandleApply;
import cn.shiying.common.entity.patient_handle.PatientHandleApplyDetailed;
import cn.shiying.common.mapper.PatientDetailedMapper;
import cn.shiying.patient_handle.client.handle;
import cn.shiying.patient_handle.entity.form.HandleApplyForm;
import cn.shiying.patient_handle.service.PatientHandleApplyDetailedService;
import cn.shiying.patient_handle.service.PatientHandleApplyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.patient_handle.service.PatientHandleService;
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
 * @since 2020-05-11
 */
@RestController
@RequestMapping("handle")
public class PatientHandleController {
    @Autowired
    private PatientHandleService handleService;

    @Autowired
    PatientHandleApplyService applyService;

    @Autowired
    PatientHandleApplyDetailedService detailedService;
    @Autowired
    handle handle;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('patient_handle:handle:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = handleService.queryPage(params);
        PageUtils page2=handleService.queryPage2(params);
        return Result.ok().put("page", page).put("page2",page2);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('patient_handle:handle:info')")
    public Result info(@PathVariable("id") String id){
       PatientHandle handle = handleService.getById(id);

        return Result.ok().put("handle", handle);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('patient_handle:handle:save')")
    public Result save(@RequestBody PatientHandle handle){
        ValidatorUtils.validateEntity(handle);
        handleService.save(handle);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('patient_handle:handle:update')")
    public Result update(@RequestBody PatientHandle handle){
        ValidatorUtils.validateEntity(handle);
        handleService.updateById(handle);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('patient_handle:handle:delete')")
    public Result delete(@RequestBody String[] ids){
        handleService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

    @PostMapping("/apply")
    @PreAuthorize("hasAuthority('patient_handle:handle:apply')")
    public Result apply(@RequestBody HandleApplyForm form){
        PatientHandleApply apply = form.getApply();
        applyService.save(apply);
        List<PatientHandleApplyDetailed> detaileds = form.getDetaileds();
        for (PatientHandleApplyDetailed detailed : detaileds) {
            detailed.setApplyId(apply.getId());
            detailed.setStatus(1);
        }
        detailedService.saveBatch(detaileds);
        return Result.ok().put("id",apply.getId());
    }

    @GetMapping("/apply/list/{registerId}")
    @PreAuthorize("hasAuthority('patient_handle:handle:apply:list')")
    public Result apply(@PathVariable("registerId") String registerId){
        PatientHandleApply apply = applyService.getOne(new QueryWrapper<PatientHandleApply>().eq("register_id", registerId));
        List<PatientHandleApplyDetailed> list = detailedService.list(new QueryWrapper<PatientHandleApplyDetailed>().eq("apply_id", apply.getId()));
        for (PatientHandleApplyDetailed detailed : list) {
            detailed.setPatientHandle(handleService.getById(detailed.getHandleId()));
        }
        return Result.ok().put("list",list).put("id",apply.getId());
    }

    @GetMapping("/apply/payment")
    @PreAuthorize("hasAuthority('patient_handle:handle:apply:list')")
    public Result payment(@RequestParam Map<String, Object> params){
        PageUtils page = handleService.queryPagePatient(params);
        return Result.ok().put("page", page);
    }

    @GetMapping("/updatestate/{id}")
    public Result updatestate(@PathVariable Integer[] id) {
        handleService.updatestate(id);
        return Result.ok();
    }

    /**
     * 执行处置
     */
    @GetMapping("/runHandle/{id}")
    public Result runHandle(@PathVariable Integer id){
        String name=getUser().getUsername();
        handleService.runHandle(name,id);
        return Result.ok();
    }

    public JwtUser getUser(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user=new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        user.setDepartmentId((List<Integer>) map.get("departmentId"));
        return user;
    }
}

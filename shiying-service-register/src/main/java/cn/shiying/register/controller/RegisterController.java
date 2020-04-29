package cn.shiying.register.controller;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.entity.token.JwtUser;
import cn.shiying.register.client.ActivitiClient;
import cn.shiying.register.client.PatienClient;
import cn.shiying.register.entity.RegisterPatient;
import cn.shiying.register.entity.Vo.RegisterPatientVO;
import cn.shiying.register.entity.Vo.departmentVo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.register.entity.Register;
import cn.shiying.register.service.RegisterService;
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
 * @since 2020-04-17
 */
@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    PatienClient patientclient;

    @Autowired
    ActivitiClient activitiClient;


    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('register:register:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = registerService.queryPage(params);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('register:register:info')")
    public Result info(@PathVariable("id") String id){
       Register register = registerService.getById(id);

        return Result.ok().put("register", register);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('register:register:save')")
    public Result save(@RequestBody RegisterPatient register){
        ValidatorUtils.validateEntity(register);
        PatientDetailed p=new PatientDetailed();
        p.setPatientName(register.getPatientName());
        p.setPatientAge(register.getPatientAge());
        p.setPatientSex(register.getPatientSex());
        p.setPatientPhone(register.getPatientPhone());
        p.setPatientAddress(register.getPatientAddress());
        p.setPatientNote(register.getPatientNote());
        p.setPatientCartnum(register.getPatientCartnum());
        Result rs=patientclient.save(p);
        Result result=activitiClient.startPatient(register.getDepartmentId());
        String processInstanceId = (String) result.get("processInstanceId");
        Integer pid=(Integer) rs.get("id");
        Register r=new Register();
        r.setPatientId(pid);
        r.setDepartmentId(register.getDepartmentId());
        r.setRegisterCost(register.getRegisterCost());
        r.setProcessInstanceId(processInstanceId);
        registerService.save(r);
        activitiClient.registerId(processInstanceId,r.getRegisterId());
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('register:register:update')")
    public Result update(@RequestBody Register register){
        ValidatorUtils.validateEntity(register);
        registerService.updateById(register);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('register:register:delete')")
    public Result delete(@RequestBody String[] ids){
        registerService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
    @RequestMapping("/all")
    public Result all(){
        List<departmentVo> list = registerService.departmentvo();
        return Result.ok().put("list",list);
    }

    @GetMapping("/refreshPatient")
    public Result refreshPatient(){
        Result result=activitiClient.registerPatient();
        List<RegisterPatientVO> deptWaitList=
                registerService.list((List<Integer>) result.get("deptWaitList"));
        List<RegisterPatientVO> personalWaitList=
                registerService.list((List<Integer>) result.get("personalWaitList"));
        List<RegisterPatientVO> personalDuringList=
                registerService.list((List<Integer>) result.get("personalDuringList"));

//        PageUtils personalEndList=registerService.listPage(null);
        return Result.ok().put("deptWaitList",deptWaitList)
                .put("personalWaitList",personalWaitList)
                .put("personalDuringList",personalDuringList);
    }
    public List<Integer> getDepartment(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (List<Integer>)map.get("departmentId");
    }
}

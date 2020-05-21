package cn.shiying.register.controller;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.common.utils.CookieUtil;
import cn.shiying.register.client.ActivitiClient;
import cn.shiying.register.client.PatienClient;
import cn.shiying.register.config.RegisterSchedule;
import cn.shiying.register.entity.RegisterPatient;
import cn.shiying.register.entity.Vo.RegisterPatientVO;
import cn.shiying.register.entity.Vo.departmentVo;
import cn.shiying.register.entity.form.RegisterForm;
import cn.shiying.register.util.CaptchaUtil;
import com.alibaba.fastjson.JSON;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.register.entity.Register;
import cn.shiying.register.service.RegisterService;
import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.validator.ValidatorUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
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

    @Autowired
    RegisterSchedule schedule;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private static final String REGEX_MOBILE ="((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";


    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('register:register:list')")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = registerService.queryPage(params);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('register:register:info')")
    public Result info(@PathVariable("id") String id) {
        Register register = registerService.getById(id);

        return Result.ok().put("register", register);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('register:register:save')")
    public Result save(@RequestBody RegisterPatient register) {

        //进货单号
        String RegisterId = schedule.createId();

        ValidatorUtils.validateEntity(register);
        PatientDetailed p = new PatientDetailed();
        p.setPatientName(register.getPatientName());
        p.setPatientAge(register.getPatientAge());
        p.setPatientSex(register.getPatientSex());
        p.setPatientPhone(register.getPatientPhone());
        p.setPatientAddress(register.getPatientAddress());
        p.setPatientNote(register.getPatientNote());
        p.setPatientCartnum(register.getPatientCartnum());
        Result rs = patientclient.save(p);
        Result result = activitiClient.startPatient(register.getDepartmentId(), RegisterId);
        String processInstanceId = (String) result.get("processInstanceId");
        Integer pid = (Integer) rs.get("id");
        Register r = new Register();
        r.setRegisterId(RegisterId);
        r.setPatientId(pid);
        r.setDepartmentId(register.getDepartmentId());
        r.setRegisterCost(register.getRegisterCost());
        r.setProcessInstanceId(processInstanceId);
        registerService.save(r);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('register:register:update')")
    public Result update(@RequestBody Register register) {
        ValidatorUtils.validateEntity(register);
        registerService.updateById(register);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('register:register:delete')")
    public Result delete(@RequestBody String[] ids) {
        registerService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }

    @RequestMapping("/all")
    public Result all() {
        List<departmentVo> list = registerService.departmentvo();
        return Result.ok().put("list", list);
    }

    @GetMapping("/refreshPatient")
    public Result refreshPatient() {
        Result result = activitiClient.registerPatient();
        List<RegisterPatientVO> deptWaitList =
                registerService.list((List<Integer>) result.get("deptWaitList"));
        List<RegisterPatientVO> personalWaitList =
                registerService.list((List<Integer>) result.get("personalWaitList"));
        List<RegisterPatientVO> personalDuringList =
                registerService.list((List<Integer>) result.get("personalDuringList"));

//        PageUtils personalEndList=registerService.listPage(null);
        return Result.ok().put("deptWaitList", deptWaitList)
                .put("personalWaitList", personalWaitList)
                .put("personalDuringList", personalDuringList);
    }

    @PostMapping("/back")
    public Result back(Integer id) {
        Register register = registerService.getById(id);
        Result result = activitiClient.back(register.getProcessInstanceId());
        if ((Integer) result.get("code") != 200) return Result.error(ErrorEnum.UNKNOWN);
        register.setStatus(0);
        registerService.updateById(register);
        return Result.ok();
    }

    public List<Integer> getDepartment() {
        Map<String, Object> map = (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (List<Integer>) map.get("departmentId");
    }

    public Result VerificationCode(String phone) {
        if (StringUtils.isEmpty(phone))return Result.error("手机号不能为空");
        if (!Pattern.matches(REGEX_MOBILE, phone)) return Result.error("请输入正确的手机号码");
        Long expire = stringRedisTemplate.getExpire("phone:"+phone, TimeUnit.SECONDS);
        long time = expire - 240;
        if (time>0) return Result.error("请稍后再发送").put("time",time);
        registerService.VerificationCode(phone);
        return Result.ok();
    }


    /**
     * @param @return 参数说明
     * @return BaseRestResult 返回类型
     * @Description: 生成滑块拼图验证码
     */
    @GetMapping("/getImageVerifyCode")
    public Result getImageVerifyCode() {
        Result resultMap = new Result();
        //读取本地路径下的图片,随机选一条
        File file = new File(this.getClass().getResource("/image").getPath());
        File[] files = file.listFiles();
        int n = new Random().nextInt(files.length);
        File imageUrl = files[n];
        CaptchaUtil.createImage(imageUrl, resultMap);

        //读取网络图片
        //ImageUtil.createImage("http://hbimg.b0.upaiyun.com/7986d66f29bfeb6015aaaec33d33fcd1d875ca16316f-2bMHNG_fw658",resultMap);
//        session.setAttribute("xWidth", resultMap.get("xWidth"));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        resultMap.put("uuid",uuid);
        stringRedisTemplate.boundValueOps("Captcha:" + uuid).set(resultMap.get("xWidth") + "", 300, TimeUnit.SECONDS);
        resultMap.remove("xWidth");
        return resultMap;
    }


    /**
     * 校验滑块拼图验证码
     *
     * @param moveLength 移动距离
     * @return BaseRestResult 返回类型
     * @Description: 生成图形验证码
     */
    @GetMapping("/verifyImageCode")
    public Result verifyImageCode(@RequestParam("moveLength") String moveLength,@RequestParam("uuid") String uuid,@RequestParam("phone") String phone) {
        Double dMoveLength = Double.valueOf(moveLength);
        Long expire = stringRedisTemplate.getExpire("Captcha:"+uuid, TimeUnit.MILLISECONDS);
        if (expire <= 0) return Result.error(ErrorEnum.LONG_TIME_ERROR);
        Integer xWidth = Integer.parseInt(stringRedisTemplate.opsForValue().get("Captcha:"+uuid));
        stringRedisTemplate.delete("Captcha:"+uuid);
        if (Math.abs(xWidth - dMoveLength) > 10) {
            return Result.error("验证不通过");
        } else {
            Result result = VerificationCode(phone);
            return Result.ok().put("time",expire).put("result",result);
        }
    }

    @PostMapping("/verifyTextCode")
    public Result verifyTextCode(@RequestParam("phone") String phone,@RequestParam("code") String code,@RequestParam("departmentId") Integer departmentId,@RequestParam("uid") Integer uid){
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)) return Result.error("手机号或验证码不能为空");
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps("phone:" + phone);
        if (ops.getExpire()<=0) return Result.error(ErrorEnum.LONG_TIME_ERROR);
        if (!ops.get().equals(code)) return Result.error(ErrorEnum.CAPTCHA_WRONG);
        Result result = patientclient.isExist(phone);
        if (result.get("pid")!=null){
            if (registerService.addCount(uid)==0){
                return Result.error("当前医生已经达到最大接诊人数了");
            }
            String RegisterId = schedule.createId();
            Result res = activitiClient.startPatient(departmentId, RegisterId);
            String processInstanceId = (String) res.get("processInstanceId");
            Register r = new Register();
            r.setRegisterId(RegisterId);
            r.setPatientId((Integer) result.get("pid"));
            r.setDepartmentId(departmentId);
            r.setProcessInstanceId(processInstanceId);
            registerService.save(r);
            activitiClient.userConsultation(processInstanceId, uid);
            return Result.ok();
        } else {
            stringRedisTemplate.boundValueOps("form:"+phone).set(JSON.toJSONString(new RegisterForm(uid,departmentId)),1200,TimeUnit.SECONDS);
            return Result.error(ErrorEnum.USERNAME_NOT_FOUND);
        }
    }
    @PostMapping("/register")
    public Result register(@RequestBody PatientDetailed register) {
        BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps("form:" + register.getPatientPhone());
        if (ops.getExpire()<=0) return Result.error(ErrorEnum.LONG_TIME_ERROR);
        RegisterForm form = JSON.parseObject(ops.get(), RegisterForm.class);
        if (form==null||form.getUid()==null||form.getDepartmentId()==null) return Result.error(ErrorEnum.LONG_TIME_ERROR);
        if (registerService.addCount(form.getUid())==0){
            return Result.error("当前医生已经达到最大接诊人数了");
        }
        double money= registerService.getMoney(form.getUid());
        String RegisterId = schedule.createId();
        Result rs = patientclient.save(register);
        Result result = activitiClient.startPatient(form.getDepartmentId(), RegisterId);
        String processInstanceId = (String) result.get("processInstanceId");
        Integer pid = (Integer) rs.get("id");
        System.out.println(pid);
        Register r = new Register();
        r.setRegisterId(RegisterId);
        r.setPatientId(pid);
        r.setDepartmentId(form.getDepartmentId());
        r.setRegisterCost(money);
        r.setProcessInstanceId(processInstanceId);
        registerService.save(r);
        activitiClient.userConsultation(processInstanceId, form.getUid());
        return Result.ok();
    }
}

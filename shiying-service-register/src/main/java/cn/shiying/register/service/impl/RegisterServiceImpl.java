package cn.shiying.register.service.impl;

import cn.shiying.common.dto.Result;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.register.client.ActivitiClient;
import cn.shiying.register.entity.Register;
import cn.shiying.register.entity.Vo.RegisterPatientVO;
import cn.shiying.register.entity.Vo.departmentVo;
import cn.shiying.register.mapper.RegisterMapper;
import cn.shiying.register.service.RegisterService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements RegisterService {

    @Autowired
    ActivitiClient activitiClient;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<departmentVo> page=new Query<departmentVo>(params).getPage();
        List<departmentVo> list=baseMapper.departmentvo(page,params);
        List<String> processInstanceIds=new ArrayList<>();
        for (departmentVo vo : list) {
            processInstanceIds.add(vo.getProcessInstanceId());
        }
        Result result=activitiClient.bpmName(processInstanceIds);
        if ((Integer) result.get("code")!=200) ExceptionCast.cast(ErrorEnum.UNKNOWN);
        Map<String,String> map=(Map<String, String>) result.get("map");
        for (departmentVo vo : list) {
            vo.setBpmName(map.get(vo.getProcessInstanceId()));
        }
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public List<departmentVo> departmentvo() {
        return baseMapper.departmentvo();
    }


    @Override
    public List<RegisterPatientVO> list(List<Integer> id) {
        if (id == null||id.size()==0) {
            return null;
        }
        return baseMapper.list(id);
    }

    @Override
    public PageUtils listPage(Map<String, Object> params) {
        Page<RegisterPatientVO> page=new Query<RegisterPatientVO>(params).getPage();
        List<RegisterPatientVO> registerPatientVOS = baseMapper.listPage(page, params);
        page.setRecords(registerPatientVOS);
        return new PageUtils(page);
    }

    @Override
    public void VerificationCode(String phone) {
        String code = String.valueOf((int)((Math.random()*9+1)*100000));
        stringRedisTemplate.boundValueOps("phone:"+phone).set(code,300, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire("phone:"+phone, TimeUnit.SECONDS);
        if (expire<=0) ExceptionCast.cast(ErrorEnum.LOAD_LANG);
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GAt6zA8RGNLqRQ6gTYF", "j1LVxtnNaFJaOrlV7BTNT1eGZFmApq");
//        IAcsClient client = new DefaultAcsClient(profile);
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers",phone);
//        request.putQueryParameter("SignName","时樱");
//        request.putQueryParameter("TemplateCode","SMS_189520898");
//        HashMap<String, String> map = new HashMap<>();
//        map.put("code",code);
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ExceptionCast.cast(ErrorEnum.LOAD_LANG);
//        }
    }

    @Override
    public Integer addCount(Integer uid) {
        return baseMapper.addCount(uid);
    }

    @Override
    public double getMoney(Integer uid) {
        return baseMapper.getMoney(uid);
    }

}

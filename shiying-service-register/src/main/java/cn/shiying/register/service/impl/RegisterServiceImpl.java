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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<departmentVo> page=new Query<departmentVo>(params).getPage();
        List<departmentVo> list=baseMapper.departmentvo(page,params);
        for (departmentVo vo : list) {
            if (vo.getStatus()==0) continue;
            vo.getProcessInstanceId();
            Result result=activitiClient.bpmName(vo.getProcessInstanceId());
            if ((Integer) result.get("code")!=200) ExceptionCast.cast(ErrorEnum.UNKNOWN);
            String bpmName=(String) result.get("bpmName");
            if ("挂号".equals(bpmName)){
                vo.setBpmName("待诊");
            }else if ("诊断完成".equals(bpmName)){
                vo.setBpmName("已诊");
            }else {
                vo.setBpmName("诊中");
            }

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

}

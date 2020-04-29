package cn.shiying.register.service.impl;

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

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<departmentVo> page=new Query<departmentVo>(params).getPage();
        List<departmentVo> list=baseMapper.departmentvo(page,params);
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

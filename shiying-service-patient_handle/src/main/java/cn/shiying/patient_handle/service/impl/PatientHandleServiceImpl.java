package cn.shiying.patient_handle.service.impl;

import cn.shiying.patient_handle.entity.PatientHandle;
import cn.shiying.patient_handle.mapper.PatientHandleMapper;
import cn.shiying.patient_handle.service.PatientHandleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-05-11
 */
@Service
public class PatientHandleServiceImpl extends ServiceImpl<PatientHandleMapper, PatientHandle> implements PatientHandleService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PatientHandle> page=baseMapper.selectPage(new Query<PatientHandle>(params).getPage(),
                new QueryWrapper<PatientHandle>().lambda());
        return new PageUtils(page);
    }

}

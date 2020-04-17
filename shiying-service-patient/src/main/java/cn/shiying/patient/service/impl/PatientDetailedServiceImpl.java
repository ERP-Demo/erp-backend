package cn.shiying.patient.service.impl;

import cn.shiying.patient.entity.PatientDetailed;
import cn.shiying.patient.mapper.PatientDetailedMapper;
import cn.shiying.patient.service.PatientDetailedService;
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
 * @since 2020-04-17
 */
@Service
public class PatientDetailedServiceImpl extends ServiceImpl<PatientDetailedMapper, PatientDetailed> implements PatientDetailedService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PatientDetailed> page=baseMapper.selectPage(new Query<PatientDetailed>(params).getPage(),
                new QueryWrapper<PatientDetailed>().lambda());
        return new PageUtils(page);
    }

}

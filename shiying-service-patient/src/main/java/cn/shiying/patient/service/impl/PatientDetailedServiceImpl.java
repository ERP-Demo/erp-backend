package cn.shiying.patient.service.impl;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.mapper.PatientDetailedMapper;
import cn.shiying.patient.service.PatientDetailedService;
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
public class PatientDetailedServiceImpl extends ServiceImpl<PatientDetailedMapper, PatientDetailed> implements PatientDetailedService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<PatientDetailed>(params).getPage();
        List<PatientDetailed> list= baseMapper.queryByCatnum(page,params);
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

}

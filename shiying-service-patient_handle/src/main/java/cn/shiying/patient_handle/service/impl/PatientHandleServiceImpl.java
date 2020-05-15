package cn.shiying.patient_handle.service.impl;

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
        QueryWrapper<PatientHandle> wrapper = new QueryWrapper<>();
        if (params.get("search")!=null&&params.get("search")!="")
            wrapper.like("handle_name",params.get("search"));
        IPage<PatientHandle> page=baseMapper.selectPage(new Query<PatientHandle>(params).getPage(),
                wrapper.lambda());
        return new PageUtils(page);
    }

}

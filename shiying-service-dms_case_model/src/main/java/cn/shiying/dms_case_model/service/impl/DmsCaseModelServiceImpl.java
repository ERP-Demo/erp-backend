package cn.shiying.dms_case_model.service.impl;

import cn.shiying.dms_case_model.entity.DmsCaseModel;
import cn.shiying.dms_case_model.mapper.DmsCaseModelMapper;
import cn.shiying.dms_case_model.service.DmsCaseModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * <p>
 * 病历模版表 服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
@Service
public class DmsCaseModelServiceImpl extends ServiceImpl<DmsCaseModelMapper, DmsCaseModel> implements DmsCaseModelService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DmsCaseModel> page=baseMapper.selectPage(new Query<DmsCaseModel>(params).getPage(),
                new QueryWrapper<DmsCaseModel>().lambda());
        return new PageUtils(page);
    }

}

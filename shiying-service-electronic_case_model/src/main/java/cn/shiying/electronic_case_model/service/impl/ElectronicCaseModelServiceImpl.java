package cn.shiying.electronic_case_model.service.impl;

import cn.shiying.electronic_case_model.entity.ElectronicCaseModel;
import cn.shiying.electronic_case_model.mapper.ElectronicCaseModelMapper;
import cn.shiying.electronic_case_model.service.ElectronicCaseModelService;
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
public class ElectronicCaseModelServiceImpl extends ServiceImpl<ElectronicCaseModelMapper, ElectronicCaseModel> implements ElectronicCaseModelService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ElectronicCaseModel> page=baseMapper.selectPage(new Query<ElectronicCaseModel>(params).getPage(),
                new QueryWrapper<ElectronicCaseModel>().lambda());
        return new PageUtils(page);
    }

}

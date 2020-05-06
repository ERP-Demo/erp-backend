package cn.shiying.electronic_case_model_catalog.service.impl;

import cn.shiying.electronic_case_model_catalog.entity.ElectronicCaseModelCatalog;
import cn.shiying.electronic_case_model_catalog.mapper.ElectronicCaseModelCatalogMapper;
import cn.shiying.electronic_case_model_catalog.service.ElectronicCaseModelCatalogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * <p>
 * 病例模版目录表
 服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
@Service
public class ElectronicCaseModelCatalogServiceImpl extends ServiceImpl<ElectronicCaseModelCatalogMapper, ElectronicCaseModelCatalog> implements ElectronicCaseModelCatalogService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ElectronicCaseModelCatalog> page=baseMapper.selectPage(new Query<ElectronicCaseModelCatalog>(params).getPage(),
                new QueryWrapper<ElectronicCaseModelCatalog>().lambda());
        return new PageUtils(page);
    }

}

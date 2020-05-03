package cn.shiying.dms_case_model_catalog.service.impl;

import cn.shiying.dms_case_model_catalog.entity.DmsCaseModelCatalog;
import cn.shiying.dms_case_model_catalog.mapper.DmsCaseModelCatalogMapper;
import cn.shiying.dms_case_model_catalog.service.DmsCaseModelCatalogService;
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
public class DmsCaseModelCatalogServiceImpl extends ServiceImpl<DmsCaseModelCatalogMapper, DmsCaseModelCatalog> implements DmsCaseModelCatalogService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DmsCaseModelCatalog> page=baseMapper.selectPage(new Query<DmsCaseModelCatalog>(params).getPage(),
                new QueryWrapper<DmsCaseModelCatalog>().lambda());
        return new PageUtils(page);
    }

}

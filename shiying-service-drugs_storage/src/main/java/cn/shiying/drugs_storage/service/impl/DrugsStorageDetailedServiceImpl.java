package cn.shiying.drugs_storage.service.impl;

import cn.shiying.drugs_storage.entity.DrugsStorageDetailed;
import cn.shiying.drugs_storage.mapper.DrugsStorageDetailedMapper;
import cn.shiying.drugs_storage.service.DrugsStorageDetailedService;
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
public class DrugsStorageDetailedServiceImpl extends ServiceImpl<DrugsStorageDetailedMapper, DrugsStorageDetailed> implements DrugsStorageDetailedService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DrugsStorageDetailed> page=baseMapper.selectPage(new Query<DrugsStorageDetailed>(params).getPage(),
                new QueryWrapper<DrugsStorageDetailed>().lambda());
        return new PageUtils(page);
    }

}

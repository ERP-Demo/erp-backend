package cn.shiying.drugs.service.impl;


import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.mapper.DrugsDetailedMapper;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.drugs.service.DrugsDetailedService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
@Service
public class DrugsDetailedServiceImpl extends ServiceImpl<DrugsDetailedMapper, DrugsDetailed> implements DrugsDetailedService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DrugsDetailed> page=baseMapper.selectPage(new Query<DrugsDetailed>(params).getPage(),
                new QueryWrapper<DrugsDetailed>().lambda());
        return new PageUtils(page);
    }

}

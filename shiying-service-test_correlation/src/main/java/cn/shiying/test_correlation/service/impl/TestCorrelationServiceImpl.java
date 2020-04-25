package cn.shiying.test_correlation.service.impl;

import cn.shiying.test_correlation.entity.TestCorrelation;
import cn.shiying.test_correlation.mapper.TestCorrelationMapper;
import cn.shiying.test_correlation.service.TestCorrelationService;
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
 * @since 2020-04-23
 */
@Service
public class TestCorrelationServiceImpl extends ServiceImpl<TestCorrelationMapper, TestCorrelation> implements TestCorrelationService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TestCorrelation> page=baseMapper.selectPage(new Query<TestCorrelation>(params).getPage(),
                new QueryWrapper<TestCorrelation>().lambda());
        return new PageUtils(page);
    }

}

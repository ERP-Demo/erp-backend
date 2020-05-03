package cn.shiying.test_correlation.service.impl;

import cn.shiying.test_correlation.entity.TestCorrelation;
import cn.shiying.test_correlation.entity.vo.TestCorrelationVO;
import cn.shiying.test_correlation.mapper.TestCorrelationMapper;
import cn.shiying.test_correlation.service.TestCorrelationService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2020-04-28
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
        Page page=new Query<TestCorrelation>(params).getPage();
        List<TestCorrelationVO> list= baseMapper.list(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }

}

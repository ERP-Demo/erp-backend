package cn.shiying.test_correlationaffiliate.service.impl;

import cn.shiying.test_correlationaffiliate.entity.TestCorrelation;
import cn.shiying.test_correlationaffiliate.entity.vo.TestCorrelationAffiliateVO;
import cn.shiying.test_correlationaffiliate.mapper.TestCorrelationMapper;
import cn.shiying.test_correlationaffiliate.service.TestCorrelationService;
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
 * @since 2020-04-26
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
        List<TestCorrelationAffiliateVO> list=baseMapper.listTestCorrelationAffiliateVO(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }
//下拉框显示子项目
    @Override
    public List<TestCorrelation> selectTestCorrelationId() {
        return baseMapper.TestCorrelationId();
    }
//    添加
    @Override
    public void addTestCorrelation(TestCorrelation testcorrelation) { baseMapper.addTestCorrelation(testcorrelation); }
}

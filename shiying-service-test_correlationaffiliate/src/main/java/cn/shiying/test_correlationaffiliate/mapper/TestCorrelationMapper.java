package cn.shiying.test_correlationaffiliate.mapper;

import cn.shiying.test_correlationaffiliate.entity.TestCorrelation;
import cn.shiying.test_correlationaffiliate.entity.vo.TestCorrelationAffiliateVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-26
 */
@Mapper
public interface TestCorrelationMapper extends BaseMapper<TestCorrelation> {


    List<TestCorrelationAffiliateVO> listTestCorrelationAffiliateVO(Page page, Map<String, Object> params);

    List<TestCorrelationAffiliateVO> listTestCorrelationAffiliateProjectsVO(Page page, Map<String, Object> params);
}

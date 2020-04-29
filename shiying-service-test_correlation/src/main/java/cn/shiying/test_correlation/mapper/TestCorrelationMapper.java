package cn.shiying.test_correlation.mapper;

import cn.shiying.test_correlation.entity.TestCorrelation;
import cn.shiying.test_correlation.entity.VO.TestCorrelationVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-28
 */
@Mapper
public interface TestCorrelationMapper extends BaseMapper<TestCorrelation> {
    List<TestCorrelationVO> list(Page<TestCorrelationVO> page, @Param("params")Map<String,Object> params);
}

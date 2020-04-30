package cn.shiying.test_synthesize.mapper;

import cn.shiying.test_synthesize.entity.TestSynthesize;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-23
 */
@Mapper
public interface TestSynthesizeMapper extends BaseMapper<TestSynthesize> {
    List<TestSynthesize> queryByCatnum(Page<TestSynthesize> page, @Param("params") Map<String, Object> params);
}

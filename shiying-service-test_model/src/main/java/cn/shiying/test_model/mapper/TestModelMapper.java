package cn.shiying.test_model.mapper;

import cn.shiying.test_model.entity.TestModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-05-05
 */
@Mapper
public interface TestModelMapper extends BaseMapper<TestModel> {
    void add(@Param("testModelId") Integer testModelId, @Param("ids") Integer ids);
}

package cn.shiying.test_model.mapper;

import cn.shiying.test_model.entity.TestModel;
import cn.shiying.test_model.entity.vo.TestModelVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    void del(@Param("ids") List<String> ids);
    List<TestModelVo> selectById(@Param("id") Integer id);
    TestModel selectbyid(@Param("id") Integer id);
    void delbyid(@Param("testModelId") Integer testModelId);
}

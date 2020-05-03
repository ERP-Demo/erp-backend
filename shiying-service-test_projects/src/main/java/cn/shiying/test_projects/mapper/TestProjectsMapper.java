package cn.shiying.test_projects.mapper;

import cn.shiying.test_projects.entity.TestProjects;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-29
 */
@Mapper
public interface TestProjectsMapper extends BaseMapper<TestProjects> {

    List<TestProjects> boxTestProjects();
}

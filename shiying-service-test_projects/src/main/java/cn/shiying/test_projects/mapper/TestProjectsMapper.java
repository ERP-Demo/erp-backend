package cn.shiying.test_projects.mapper;

import cn.shiying.common.entity.TestSynthesize.TestSynthesize;
import cn.shiying.test_projects.entity.TestProjects;
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
 * @since 2020-04-29
 */
@Mapper
public interface TestProjectsMapper extends BaseMapper<TestProjects> {

    List<TestProjects> ProjectsList(Page<TestSynthesize> page, @Param("params") Map<String, Object> params);
}

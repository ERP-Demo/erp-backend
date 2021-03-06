package cn.shiying.requirements.mapper;

import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.TestSynthesizeAll;
import cn.shiying.requirements.entity.Vo.Requirements_Vo;
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
 * @since 2020-05-02
 */
@Mapper
public interface RequirementsMapper extends BaseMapper<Requirements> {
    void updatestate(@Param("id") Integer id);
    List<Requirements_Vo> All(Page<Requirements_Vo> page, @feign.Param("params") Map<String, Object> params);

    List<TestSynthesizeAll> TestSynthesizeAll(@Param("id") String id);
    List<TestSynthesizeAll> topFive(@Param("uid") Integer uid);
}

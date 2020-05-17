package cn.shiying.requirements.mapper;

import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.TestSynthesizeAll;
import cn.shiying.requirements.entity.Vo.Requirements_Vo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<Requirements_Vo>All();
    List<TestSynthesizeAll> TestSynthesizeAll();
    List<TestSynthesizeAll> topFive(@Param("uid") Integer uid);
}

package cn.shiying.register.mapper;

import cn.shiying.register.entity.Register;
import cn.shiying.register.entity.Vo.RegisterPatientVO;
import cn.shiying.register.entity.Vo.departmentVo;
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
 * @since 2020-04-17
 */
@Mapper
public interface RegisterMapper extends BaseMapper<Register> {
    List<departmentVo> departmentvo(Page<departmentVo> page,@Param("params") Map<String, Object> params);

    List<RegisterPatientVO> list(@Param("ids") List<Integer> ids);

    List<RegisterPatientVO> listPage(Page page,@Param("params") Map<String, Object> params);

    List<departmentVo> departmentvo();
}

package cn.shiying.register.mapper;

import cn.shiying.common.entity.department.Department;
import cn.shiying.register.entity.Register;
import cn.shiying.register.entity.Vo.departmentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    List<departmentVo> departmentvo();
}

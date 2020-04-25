package cn.shiying.users_department.mapper;

import cn.shiying.users_department.entity.UsersDepartment;
import cn.shiying.users_department.entity.vo.UsersDepartmentVo;
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
public interface UsersDepartmentMapper extends BaseMapper<UsersDepartment> {
    List<UsersDepartmentVo> selectlike(Page<UsersDepartmentVo> page , @Param("params")Map<String,Object> params);
    List<UsersDepartmentVo> All(@Param("uid")  Long uid);
    void delById(@Param("userId") Long userId,@Param("departmentId") Integer departmentId);
}

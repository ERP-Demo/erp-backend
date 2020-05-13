package cn.shiying.users_department.mapper;

import cn.shiying.users_department.entity.User;
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
    List<UsersDepartment> All(Integer uid);
    void delById(@Param("id") Integer id,@Param("userId") Long userId);
    void add(@Param("uid") Integer uid, @Param("ids") Integer ids);
    List<Long> selUser(@Param("did") Integer did);
    List<User> selById(@Param("uid") List<Long> uid);
}

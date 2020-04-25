package cn.shiying.users_department.service;

import cn.shiying.users_department.entity.UsersDepartment;
import cn.shiying.users_department.entity.vo.UsersDepartmentVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import feign.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-23
 */
public interface UsersDepartmentService extends IService<UsersDepartment> {
    List<UsersDepartmentVo> All(@Param("uid")  Long uid);
    void delById(@Param("userId") Long userId,@Param("departmentId") Integer departmentId);

}

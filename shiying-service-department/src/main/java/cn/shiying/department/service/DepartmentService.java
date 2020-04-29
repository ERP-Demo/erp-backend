package cn.shiying.department.service;

import cn.shiying.common.entity.department.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import feign.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
public interface DepartmentService extends IService<Department> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
     Department selectById(@Param("did") Integer did);
}

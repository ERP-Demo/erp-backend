package cn.shiying.users_department.service.impl;

import cn.shiying.users_department.entity.UsersDepartment;
import cn.shiying.users_department.entity.vo.UsersDepartmentVo;
import cn.shiying.users_department.mapper.UsersDepartmentMapper;
import cn.shiying.users_department.service.UsersDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-23
 */
@Service
public class UsersDepartmentServiceImpl extends ServiceImpl<UsersDepartmentMapper, UsersDepartment> implements UsersDepartmentService {

    @Override
    public List<UsersDepartmentVo> All(Long uid) {
        return baseMapper.All(uid);
    }

    @Override
    public void delById(Long userId, Integer departmentId) {
        baseMapper.delById(userId,departmentId);
    }
}

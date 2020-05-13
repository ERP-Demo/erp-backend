package cn.shiying.users_department.service.impl;

import cn.shiying.common.entity.department.Department;
import cn.shiying.users_department.entity.User;
import cn.shiying.users_department.entity.UsersDepartment;
import cn.shiying.users_department.mapper.UsersDepartmentMapper;
import cn.shiying.users_department.service.UsersDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<UsersDepartment> All(Integer uid) {
        return baseMapper.All(uid);
    }

    @Override
    public void delById(Integer id,Long userId) {
        baseMapper.delById(id,userId);
    }

    @Override
    public void add(Integer uid, Integer[] ids) {
        for(Integer i :ids){
            baseMapper.add(uid,i);
        }
    }

    @Override
    public List<Integer> all(Integer uid) {
        List<UsersDepartment> departments=baseMapper.All(uid);
        List<Integer> list=new ArrayList<>();
        for (UsersDepartment department : departments) {
            list.add(department.getDepartmentId());
        }
        return list;
    }

    public List<User> allUser(Integer did){
        return baseMapper.selById(baseMapper.selUser(did));
    }
}

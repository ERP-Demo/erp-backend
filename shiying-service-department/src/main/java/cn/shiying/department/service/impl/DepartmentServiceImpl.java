package cn.shiying.department.service.impl;

import cn.shiying.common.entity.department.Department;
import cn.shiying.common.mapper.DepartmentMapper;
import cn.shiying.department.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Department> page=baseMapper.selectPage(new Query<Department>(params).getPage(),
                new QueryWrapper<Department>().lambda());
        return new PageUtils(page);
    }

}

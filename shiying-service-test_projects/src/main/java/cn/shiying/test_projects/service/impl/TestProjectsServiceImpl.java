package cn.shiying.test_projects.service.impl;

import cn.shiying.test_projects.entity.TestProjects;
import cn.shiying.test_projects.mapper.TestProjectsMapper;
import cn.shiying.test_projects.service.TestProjectsService;
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
 * @since 2020-04-29
 */
@Service
public class TestProjectsServiceImpl extends ServiceImpl<TestProjectsMapper, TestProjects> implements TestProjectsService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TestProjects> page=baseMapper.selectPage(new Query<TestProjects>(params).getPage(),
                new QueryWrapper<TestProjects>().lambda());
        return new PageUtils(page);
    }

}

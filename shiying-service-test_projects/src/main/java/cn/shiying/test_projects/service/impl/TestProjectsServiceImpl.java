package cn.shiying.test_projects.service.impl;

import cn.shiying.common.entity.TestSynthesize.TestSynthesize;
import cn.shiying.common.entity.TestSynthesize_.TestSynthesize.TestSynthesize;
import cn.shiying.test_projects.entity.TestProjects;
import cn.shiying.test_projects.mapper.TestProjectsMapper;
import cn.shiying.test_projects.service.TestProjectsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        Page page=new Query<TestSynthesize>(params).getPage();
        List<TestProjects> list= baseMapper.ProjectsList(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public List<TestProjects> boxTestProjects() {
        return baseMapper.boxTestProjects();
    }

}

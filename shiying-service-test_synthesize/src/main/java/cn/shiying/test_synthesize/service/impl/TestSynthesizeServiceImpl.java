package cn.shiying.test_synthesize.service.impl;

import cn.shiying.test_synthesize.entity.TestSynthesize;
import cn.shiying.test_synthesize.mapper.TestSynthesizeMapper;
import cn.shiying.test_synthesize.service.TestSynthesizeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class TestSynthesizeServiceImpl extends ServiceImpl<TestSynthesizeMapper, TestSynthesize> implements TestSynthesizeService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<TestSynthesize>(params).getPage();
        List<TestSynthesize> list= baseMapper.queryByCatnum(page,params);
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

}

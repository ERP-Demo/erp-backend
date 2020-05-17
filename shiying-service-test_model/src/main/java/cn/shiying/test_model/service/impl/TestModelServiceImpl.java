package cn.shiying.test_model.service.impl;

import cn.shiying.test_model.entity.TestModel;
import cn.shiying.test_model.entity.vo.TestModelVo;
import cn.shiying.test_model.mapper.TestModelMapper;
import cn.shiying.test_model.service.TestModelService;
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
 * @since 2020-05-05
 */
@Service
public class TestModelServiceImpl extends ServiceImpl<TestModelMapper, TestModel> implements TestModelService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TestModel> page=baseMapper.selectPage(new Query<TestModel>(params).getPage(),
                new QueryWrapper<TestModel>().lambda());
        return new PageUtils(page);
    }
    @Override
    public void add(Integer testModelId, List<Integer> ids) {
        for(Integer i :ids){
            baseMapper.add(testModelId,i);
        }
    }
    //删除
    @Override
    public void del(List<String> ids) {
        baseMapper.del(ids);
    }
    @Override
    public List<TestModelVo> selectById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public TestModel selectbyid(Integer id) {
        return baseMapper.selectbyid(id);
    }

    @Override
    public void delbyid(Integer testModelId) {
        baseMapper.delbyid(testModelId);
    }
}

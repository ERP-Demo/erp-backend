package cn.shiying.drug_model.service.impl;

import cn.shiying.drug_model.entity.DrugModel;
import cn.shiying.drug_model.mapper.DrugModelMapper;
import cn.shiying.drug_model.service.DrugModelService;
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
 * @since 2020-05-01
 */
@Service
public class DrugModelServiceImpl extends ServiceImpl<DrugModelMapper, DrugModel> implements DrugModelService {
    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DrugModel> page=baseMapper.selectPage(new Query<DrugModel>(params).getPage(),
                new QueryWrapper<DrugModel>().lambda());
        return new PageUtils(page);
    }

    @Override
    public void add(Integer drugModelId, List<Integer> ids) {
        for(Integer i :ids){
            baseMapper.add(drugModelId,i);
        }
    }

}

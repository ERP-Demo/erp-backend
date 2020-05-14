package cn.shiying.drug_model.service.impl;

import cn.shiying.drug_model.entity.DrugModel;
import cn.shiying.drug_model.entity.from.DrugModelFrom;
import cn.shiying.drug_model.entity.vo.DrugModelVo;
import cn.shiying.drug_model.mapper.DrugModelMapper;
import cn.shiying.drug_model.service.DrugModelService;
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
        Page page=new Query<DrugModel>(params).getPage();
        List<DrugModel> list= baseMapper.queryByNameorRange(page,params);
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public void add(Integer drugModelId, List<Integer> ids) {
        for(Integer i :ids){
            baseMapper.add(drugModelId,i);
        }
    }

    @Override
    public void del(List<String> ids) {
        baseMapper.del(ids);
    }

    @Override
    public List<DrugModelVo> selectById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public DrugModel selectbyid(Integer id) {
        return baseMapper.selectbyid(id);
    }

    @Override
    public void delbyid(Integer DrugModelId) {
        baseMapper.delbyid(DrugModelId);
    }

}

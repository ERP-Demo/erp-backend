package cn.shiying.drugs_purchase.service.impl;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import cn.shiying.drugs_purchase.mapper.DrugsPurchaseMapper;
import cn.shiying.drugs_purchase.service.DrugsPurchaseService;
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
 * @since 2020-04-22
 */
@Service
public class DrugsPurchaseServiceImpl extends ServiceImpl<DrugsPurchaseMapper, DrugsPurchase> implements DrugsPurchaseService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DrugsPurchase> page=baseMapper.selectPage(new Query<DrugsPurchase>(params).getPage(),
                new QueryWrapper<DrugsPurchase>().lambda());
        return new PageUtils(page);
    }

    public List<DrugsPurchaseDetailedVO> selectBypid(String pid) { return baseMapper.selectBypid(pid); }
    public List<SupplierDetailed> selectSname() { return baseMapper.selectSname(); }
    public List<DrugsDetailed> selectDname(){ return baseMapper.selectDname(); }
    public int insertPurchase(DrugsPurchase drugsPurchase){ return baseMapper.insertPurchase(drugsPurchase); }
    public int insertDetailed(DrugsPurchaseDetailed drugsPurchaseDetailed){ return baseMapper.insertDetailed(drugsPurchaseDetailed); };
}

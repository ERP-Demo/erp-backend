package cn.shiying.supplier.service.impl;

import cn.shiying.supplier.entity.SupplierDetailed;
import cn.shiying.supplier.entity.vo.Drugs_detailedVo;
import cn.shiying.supplier.mapper.SupplierDetailedMapper;
import cn.shiying.supplier.service.SupplierDetailedService;
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
 * @since 2020-04-16
 */
@Service
public class SupplierDetailedServiceImpl extends ServiceImpl<SupplierDetailedMapper, SupplierDetailed> implements SupplierDetailedService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SupplierDetailed> page=baseMapper.selectPage(new Query<SupplierDetailed>(params).getPage(),
                new QueryWrapper<SupplierDetailed>().lambda());
        return new PageUtils(page);
    }

    @Override
    public void insertDrigs_supplier(Integer pid, Integer[] ids) {
        for(Integer i :ids){
            baseMapper.insertDrigs_supplier(pid,i);
        }
    }

    @Override
    public List<Drugs_detailedVo> lisedetailedVo() {
        return  baseMapper.lisedetailedVo();
    }

    @Override
    public List<SupplierDetailed> selectlike(String name) {
        return baseMapper.selectlike(name);
    }

}

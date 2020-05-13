package cn.shiying.drugsSupplier.service.impl;

import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugsSupplier.entity.DrugsSupplier;
import cn.shiying.drugsSupplier.mapper.DrugsSupplierMapper;
import cn.shiying.drugsSupplier.service.DrugsSupplierService;
import cn.shiying.drugsSupplier.entity.vo.DrugsSupplierVO;
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
class DrugsSupplierServiceImpl extends ServiceImpl<DrugsSupplierMapper, DrugsSupplier> implements DrugsSupplierService {


    @Override
    public List<DrugsSupplierVO> All(Integer pid) {
        return baseMapper.All(pid);
    }

    @Override
    public void deletebyid(Integer id, Integer did) {
        baseMapper.deletebyid(id,did);
    }

    @Override
    public void add(Integer tid, Integer[] id) {
        for (Integer integer : id) {
            baseMapper.add(tid,integer);
        }
    }
}

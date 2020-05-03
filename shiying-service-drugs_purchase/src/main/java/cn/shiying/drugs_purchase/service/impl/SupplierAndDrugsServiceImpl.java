package cn.shiying.drugs_purchase.service.impl;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.drugs_purchase.entity.DrugsSupplier;
import cn.shiying.drugs_purchase.entity.vo.DrugsSupplierVO;
import cn.shiying.drugs_purchase.mapper.SupplierAndDrugsMapper;
import cn.shiying.drugs_purchase.service.SupplierAndDrugsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SupplierAndDrugsServiceImpl extends ServiceImpl<SupplierAndDrugsMapper, DrugsSupplier> implements SupplierAndDrugsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<DrugsSupplier>(params).getPage();
        List<DrugsSupplierVO> list= baseMapper.listDrugsSupplierVO(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }
}

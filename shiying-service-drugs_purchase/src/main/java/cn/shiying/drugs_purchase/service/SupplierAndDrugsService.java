package cn.shiying.drugs_purchase.service;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs_purchase.entity.DrugsSupplier;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SupplierAndDrugsService extends IService<DrugsSupplier> {
    PageUtils queryPage(Map<String, Object> params);
}

package cn.shiying.supplier.service;

import cn.shiying.common.entity.supplier.SupplierDetailed;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
public interface SupplierDetailedService extends IService<SupplierDetailed> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

     //插入关系
     void insertDrigs_supplier(Integer pid,Integer[] ids);
     //模糊查询
    List<SupplierDetailed> like(String name);
}

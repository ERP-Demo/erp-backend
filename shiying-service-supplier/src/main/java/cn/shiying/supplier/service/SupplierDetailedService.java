package cn.shiying.supplier.service;

import cn.shiying.supplier.entity.SupplierDetailed;
import cn.shiying.supplier.entity.vo.Drugs_detailedVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

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

     //表与表查询
     List<Drugs_detailedVo> lisedetailedVo();

     //模糊查询
     List<SupplierDetailed> selectlike(String name);
}

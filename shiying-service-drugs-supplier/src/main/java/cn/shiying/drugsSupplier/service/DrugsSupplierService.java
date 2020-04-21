package cn.shiying.drugsSupplier.service;

import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugsSupplier.entity.DrugsSupplier;
import cn.shiying.drugsSupplier.entity.vo.DrugsSupplierVO;
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
public interface DrugsSupplierService extends IService<DrugsSupplier> {
    List<DrugsSupplierVO> All(Integer pid);


}

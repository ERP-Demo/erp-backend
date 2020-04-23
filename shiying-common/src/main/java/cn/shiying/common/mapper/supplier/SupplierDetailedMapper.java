package cn.shiying.common.mapper.supplier;

import cn.shiying.common.entity.supplier.SupplierDetailed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
@Mapper
public interface SupplierDetailedMapper extends BaseMapper<SupplierDetailed> {
    void insertDrigs_supplier(@Param("pid") Integer pid, @Param("ids") Integer ids);
    List<SupplierDetailed> like(@Param("name") String name);
}

package cn.shiying.supplier.mapper;

import cn.shiying.supplier.entity.SupplierDetailed;
import cn.shiying.supplier.entity.vo.Drugs_detailedVo;
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
    //插入关系
    void insertDrigs_supplier(@Param("pid") Integer pid, @Param("ids") Integer ids);
    //表查询
    List<Drugs_detailedVo> lisedetailedVo();
    //模糊查询
    List<SupplierDetailed> selectlike(@Param("name") String name);
}

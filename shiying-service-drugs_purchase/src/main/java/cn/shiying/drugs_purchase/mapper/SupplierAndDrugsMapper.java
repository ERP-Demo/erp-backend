package cn.shiying.drugs_purchase.mapper;

import cn.shiying.drugs_purchase.entity.DrugsSupplier;
import cn.shiying.drugs_purchase.entity.vo.DrugsSupplierVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SupplierAndDrugsMapper extends BaseMapper<DrugsSupplier> {

    /*根据供应商查询对应的药品*/
    List<DrugsSupplierVO> listDrugsSupplierVO(Page<DrugsSupplier> page, @Param("params") Map<String, Object> params);
}

package cn.shiying.drugsSupplier.mapper;

import cn.shiying.drugsSupplier.entity.DrugsSupplier;
import cn.shiying.drugsSupplier.entity.vo.DrugsSupplierVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
@Mapper
public interface DrugsSupplierMapper extends BaseMapper<DrugsSupplier> {
    List<DrugsSupplierVO> selectlike(Page<DrugsSupplierVO> page, @Param("params") Map<String,Object> params);
    List<DrugsSupplierVO> All(@Param("pid") Integer pid);
    void deletebyid(@Param("id") Integer id, @Param("did") Integer did);
    void add(@Param("tid") Integer tid, @Param("id") Integer id);
}

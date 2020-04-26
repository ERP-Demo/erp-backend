package cn.shiying.common.mapper;
import cn.shiying.common.entity.patient.PatientDetailed;
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
 * @since 2020-04-17
 */
@Mapper
public interface PatientDetailedMapper extends BaseMapper<PatientDetailed> {
    List<PatientDetailed> queryByCatnum(Page<PatientDetailed> page, @Param("params") Map<String, Object> params);
}

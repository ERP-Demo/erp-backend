package cn.shiying.patient_handle.mapper;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.entity.patient_handle.PatientHandle;
import cn.shiying.common.entity.patient_handle.PatientHandleApplyDetailed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-05-11
 */
@Mapper
public interface PatientHandleMapper extends BaseMapper<PatientHandle> {
    void updatestate(@Param("id") Integer id);



    //根据患者编号查询患者名称
    PatientHandleApplyDetailed getPatientDeId(Integer patientId);
}

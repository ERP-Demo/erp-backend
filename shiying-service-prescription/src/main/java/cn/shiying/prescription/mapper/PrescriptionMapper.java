package cn.shiying.prescription.mapper;

import cn.shiying.prescription.entity.Prescription;
import cn.shiying.prescription.entity.PrescriptionDetails;
import cn.shiying.prescription.entity.Prescription_Vo;
import cn.shiying.prescription.entity.from.DrugsAndDetailed;
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
 * @since 2020-04-30
 */
@Mapper
public interface PrescriptionMapper extends BaseMapper<Prescription> {
    void addpPrescription(Prescription prescription);
    void addpPrescriptionDetails(List<PrescriptionDetails> prescriptionDetails);
    void toVoid(List<Integer> ids);

    List<Prescription_Vo> PrescriptionVo();

    Prescription_Vo PrescriptionVoByid(@Param("id") Integer id);

    void updatestate(@Param("id") Integer id);



    List<DrugsAndDetailed> AllbyPid(Integer id);

    void bypdid(Integer id);

    List<Prescription_Vo> Prescription_VoAll(Page<Prescription_Vo> page, @feign.Param("params") Map<String, Object> params);
}

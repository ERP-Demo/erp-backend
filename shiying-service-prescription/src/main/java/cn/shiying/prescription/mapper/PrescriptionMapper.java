package cn.shiying.prescription.mapper;

import cn.shiying.prescription.entity.Prescription;
import cn.shiying.prescription.entity.PrescriptionDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}

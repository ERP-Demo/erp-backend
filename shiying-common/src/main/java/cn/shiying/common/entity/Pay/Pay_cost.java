package cn.shiying.common.entity.Pay;

import cn.shiying.common.entity.patient_handle.PatientHandleApplyDetailed;
import cn.shiying.common.entity.Requirements;
import cn.shiying.common.entity.TestSynthesize.TestSynthesize;
import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.entity.prescription.Prescription;
import cn.shiying.common.entity.prescription.PrescriptionDetails;
import lombok.Data;

/**
 * 缴费数据
 */
@Data
public class Pay_cost {
    //患者
    private PatientDetailed patientDetailed;
    //开立项目
    private Requirements requirements;
    //项目
    private TestSynthesize testSynthesize;
    //处置
    private PatientHandleApplyDetailed patientHandleApplyDetailed;
    //药品
    private Prescription prescription;
    //药品开立
    private PrescriptionDetails prescriptionDetails;
}

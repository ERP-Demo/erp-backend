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
    //患者id
    private Integer patientId;
    //患者姓名
    private String patientName;
    //就诊号
    private String registerId;
    //项目id
    private Integer testSynthesizeId;
    //项目名称
    private String testSynthesizeName;
    //项目价格
    private double testSynthesizePrice;
    //处置名称
    private String handleName;
    //处置价格
    private Double handlePrice;
    //处方名称
    private String prescriptionName;
    //处方价格
    private Double prescriptionPrice;
    //状态
    private Integer status;
    //药品id
    private Integer drugsId;
    //药品名称
    private String drugsName;
    //药品数量
    private Integer drugsNum;
    //药品开立
    private PrescriptionDetails prescriptionDetails;
}

package cn.shiying.patient_handle.entity.form;

import cn.shiying.common.entity.patient_handle.PatientHandleApply;
import cn.shiying.common.entity.patient_handle.PatientHandleApplyDetailed;
import lombok.Data;

import java.util.List;

@Data
public class HandleApplyForm {
    private PatientHandleApply apply;
    private List<PatientHandleApplyDetailed> detaileds;
}

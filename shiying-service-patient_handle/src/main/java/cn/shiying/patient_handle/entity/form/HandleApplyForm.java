package cn.shiying.patient_handle.entity.form;

import cn.shiying.patient_handle.entity.PatientHandleApply;
import cn.shiying.patient_handle.entity.PatientHandleApplyDetailed;
import lombok.Data;

import java.util.List;

@Data
public class HandleApplyForm {
    private PatientHandleApply apply;
    private List<PatientHandleApplyDetailed> detaileds;
}

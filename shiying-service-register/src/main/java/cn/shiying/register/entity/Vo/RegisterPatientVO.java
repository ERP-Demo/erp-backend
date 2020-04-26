package cn.shiying.register.entity.Vo;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.register.entity.Register;
import lombok.Data;

@Data
public class RegisterPatientVO extends Register {
    private PatientDetailed patient;
}

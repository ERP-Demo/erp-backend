package cn.shiying.prescription.entity;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.patient.PatientDetailed;
import lombok.Data;

@Data
public class Prescription_Vo extends Prescription {
    private DrugsDetailed drugsDetailed;
    private Prescription prescription;
    private PatientDetailed patientDetailed;
}

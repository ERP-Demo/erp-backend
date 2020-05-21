package cn.shiying.prescription.entity;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.patient.PatientDetailed;
import lombok.Data;

import java.util.List;

@Data
public class Prescription_Vo extends Prescription {
    private DrugsDetailed drugsDetailed;
    private Prescription prescription;
    private PatientDetailed patientDetailed;
    private String patientName;
    private String username;
    private List<PrescriptionDetails> prescriptionDetails;
}

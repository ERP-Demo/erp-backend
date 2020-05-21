package cn.shiying.prescription.entity;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import lombok.Data;

@Data
public class Prescription_Vo extends Prescription {
    private DrugsDetailed drugsDetailed;
    private Prescription prescription;
    private String patientName;
    private String username;
    private Integer status;

}

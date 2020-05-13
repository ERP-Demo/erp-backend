package cn.shiying.requirements.entity.Vo;

import cn.shiying.common.entity.TestSynthesize.TestSynthesize;
import cn.shiying.common.entity.electronicCase.ElectronicCase;
import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.requirements.entity.Requirements;
import lombok.Data;

@Data
public class Requirements_Vo extends Requirements{
    //患者
    private PatientDetailed patientDetailed;
    private Requirements requirements1;
    private TestSynthesize testSynthesize;
}

package cn.shiying.electronic_case.entity.vo;

import cn.shiying.common.entity.department.Department;
import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.entity.sys.SysUser;
import cn.shiying.electronic_case.entity.ElectronicCase;
import lombok.Data;

@Data
public class ElectronicCaseVO extends ElectronicCase {
    /**
     * 患者
     */
    private String departmentName;


    /**
     * 医生
     */
//    private SysUser user;
    /**
     * 科室
     */
    private String patientName;
}

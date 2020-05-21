package cn.shiying.register.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RegisterPatient {
    //患者名称
    private String patientName;
    //挂号费用
    private double registerCost;
    //挂号科室
    private Integer departmentId;
    //患者年龄
    private Integer patientAge;
    //患者性别
    private String patientSex;
    //患者身份证
    private String patientCartnum;
    //患者电话
    private String patientPhone;
    //患者地址
    private String patientAddress;
    //描述
    private String patientNote;

}

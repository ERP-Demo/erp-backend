package cn.shiying.patient.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PatientDetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 患者id
     */
    @TableId(value = "Patient_id", type = IdType.AUTO)
    private Integer patientId;

    /**
     * 患者姓名
     */
    @TableField("Patient_name")
    private String patientName;

    /**
     * 患者年龄
     */
    @TableField("Patient_age")
    private Integer patientAge;

    /**
     * 患者性别
     */
    @TableField("Patient_sex")
    private String patientSex;

    /**
     * 患者联系电话
     */
    @TableField("Patient_phone")
    private String patientPhone;

    /**
     * 患者联系地址
     */
    @TableField("Patient_address")
    private String patientAddress;

    /**
     * 患者入院时间
     */
    @TableField("Patient_Admission")
    private LocalDateTime patientAdmission;

    /**
     * 诊断医生
     */
    @TableField("Patient_doctor")
    private String patientDoctor;

    /**
     * 备注
     */
    @TableField("Patient_note")
    private String patientNote;


}

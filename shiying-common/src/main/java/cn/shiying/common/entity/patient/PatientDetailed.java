package cn.shiying.common.entity.patient;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @TableId(value = "patient_id", type = IdType.AUTO)
    private Integer patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 患者年龄
     */
    private Integer patientAge;

    /**
     * 患者性别
     */
    private String patientSex;

    /**
     * 患者联系电话
     */
    @TableField("patient_phone")
    private String patientPhone;

    /**
     * 患者联系地址
     */
    private String patientAddress;

    /**
     * 患者入院时间
     */
    private LocalDateTime patientAdmission;

    /**
     * 备注
     */
    private String patientNote;

    /**
     * 身份证
     */
    private String  patientCartnum;

}

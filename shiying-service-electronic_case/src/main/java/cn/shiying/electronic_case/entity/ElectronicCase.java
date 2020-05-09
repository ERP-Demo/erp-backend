package cn.shiying.electronic_case.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class ElectronicCase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 病例id
     */
    @TableId(value = "case_no", type = IdType.AUTO)
    private Integer caseNo;

    /**
     * 医生id
     */
    private Integer uid;

    /**
     * 病人id
     */
    private Integer patientId;

    /**
     * 主诉
     */
    private String complain;

    /**
     * 发病时间
     */
    private LocalDateTime onsetTime;
    /**
     * 症状
     */
    private String patientSymptom;

    /**
     * 既往病史
     */
    private String medicalHistory;
    /**
     *过敏史
     */
    private String allergyHistory;
    /**
     * 体格检查
     */
    private String healthCheckup;
    /**
     * 治疗情况
     */
    private String treatment;
    /**
     * icd编号
     */

    private LocalDateTime enterHospital;

}

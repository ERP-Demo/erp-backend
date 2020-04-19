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
     * 科室id
     */
    private Integer departmentId;

    /**
     * 入院时间
     */
    private LocalDateTime enterHospital;

    /**
     * 出院时间
     */
    private LocalDateTime leaveHospital;

    /**
     * 症状
     */
    private String patientSymptom;

    /**
     * 医嘱
     */
    private String doctorAdvice;


}

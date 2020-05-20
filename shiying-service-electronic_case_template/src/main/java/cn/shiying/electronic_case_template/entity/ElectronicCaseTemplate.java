package cn.shiying.electronic_case_template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ElectronicCaseTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模板id
     */
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    /**
     * 主诉(病人描述)
     */
    private String complain;

    /**
     * 现病史
     */
    private String patientSymptom;

    /**
     * 主要诊断
     */
    private String mainIcd;


}

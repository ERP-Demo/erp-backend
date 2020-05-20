package cn.shiying.prescription.entity;

import java.math.BigDecimal;
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
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 处方ID
     */
    @TableId(value = "prescription_id", type = IdType.AUTO)
    private Integer prescriptionId;

    /**
     * 处方名称
     */
    private String prescriptionName;

    private Double prescriptionPrice;

    private Integer prescriptionState;

    private Integer uid;

    private Integer patientId;

    private String registerId;
}

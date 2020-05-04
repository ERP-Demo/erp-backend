package cn.shiying.drugs_purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyb
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品购入编号，主键ID，主键，非空
     */
    @TableId(value = "purchase_id", type = IdType.ID_WORKER_STR)
    private String purchaseId;

    /**
     * 供应商的编号
     */
    private Integer supplierId;

    /**
     * 购入应付金额
     */
    private Double purchaseAmountPayable;

    /**
     * 购入实付金额
     */
    private Double purchaseActualAmountPaid;

    /**
     * 购入日期
     */
    private String purchaseTime;

    /**
     * 流程id
     */
    private String processInstanceId;

    /**
     * 提交人
     */
    private String subName;

    /**
     * 审核人
     */
    private String checkName;

}

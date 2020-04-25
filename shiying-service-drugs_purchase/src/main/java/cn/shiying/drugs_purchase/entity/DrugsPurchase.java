package cn.shiying.drugs_purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
     * 审核 0是未审核 1是审核通过 2是审核未通过
     */
    private Integer purchaseState;

    /**
     * 审核未通过原因
     */
    private String purchaseFailure;

    /**
     * 购入日期
     */
    private LocalDateTime purchaseTime;


}

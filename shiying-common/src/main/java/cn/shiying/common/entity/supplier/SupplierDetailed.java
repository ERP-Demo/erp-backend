package cn.shiying.common.entity.supplier;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplierDetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商的编号，主键ID，主键，非空
     */
    @TableId(value = "supplier_id", type = IdType.AUTO)
    private Integer supplierId;

    /**
     * 供应商名称，非空
     */
    private String supplierName;

    /**
     * 联系电话
     */
    private String supplierCartPhone;


    /**
     * 供应商联系人
     */
    private String supplierMan;

    /**
     * 供应商开户银行名称
     */
    private String supplierBankName;

    /**
     * 银行账号
     */
    private String supplierBankAccount;

    /**
     * 供应商地址
     */
    private String supplierAddress;



}

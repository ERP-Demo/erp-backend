package cn.shiying.drugs_purchase.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsSupplier implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
    private Integer supplierId;

    /**
     * 药品id
     */
    private Integer drugsId;
}

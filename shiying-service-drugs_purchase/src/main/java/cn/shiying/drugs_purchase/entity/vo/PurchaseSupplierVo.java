package cn.shiying.drugs_purchase.entity.vo;

import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseSupplierVo extends DrugsPurchase {

    /**
     * 供应商名称
     */
    private String supplierName;

}

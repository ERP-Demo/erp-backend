package cn.shiying.drugs_purchase.entity.vo;

import cn.shiying.drugs_purchase.entity.PurchaseReturnedDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseAndReturnedVO extends PurchaseReturnedDetailed {
    //进货单号
    private String purchaseId;

    //供应商
    private String supplierName;

    //操作人
    private String username;

    //退货时间
    private String tuihuoTime;

    private Integer status;
}

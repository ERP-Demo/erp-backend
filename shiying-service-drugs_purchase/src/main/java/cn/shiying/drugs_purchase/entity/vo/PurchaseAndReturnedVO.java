package cn.shiying.drugs_purchase.entity.vo;

import cn.shiying.drugs_purchase.entity.PurchaseReturnedDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseAndReturnedVO extends PurchaseReturnedDetailed {
    private String purchaseId;
    private String tuihuoTime;
    private String username;
}

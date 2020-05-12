package cn.shiying.drugs_purchase.entity.vo;

import cn.shiying.drugs_purchase.entity.PurchaseReturnedDetailed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ReturnedAndDetailedVO extends PurchaseReturnedDetailed {
    //药品名称
    private String drugsName;

    //进货单价
    private double pdMoney;
}

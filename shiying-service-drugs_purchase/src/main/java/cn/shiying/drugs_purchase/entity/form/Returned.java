package cn.shiying.drugs_purchase.entity.form;

import cn.shiying.drugs_purchase.entity.PurchaseReturnedDetailed;
import lombok.Data;

import java.util.List;

@Data
public class Returned {
    private List<PurchaseReturnedDetailed> purchaseReturned;
}

package cn.shiying.drugs_purchase.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseReturnedDetailed{
    private Integer pdid;
    private Integer pdNum;
    private Integer tuihuoId;
}

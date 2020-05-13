package cn.shiying.drugs_purchase.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseReturnedDetailed{
    //进货编号
    private Integer pdid;
    //退货数量
    private Integer pdNum;
    //退货编号
    private Integer tuihuoId;
}

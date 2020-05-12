package cn.shiying.drugs_purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseReturned  implements Serializable {

    //进货编号
    @TableId(value = "tuihuo_id", type = IdType.AUTO)
    private Integer tuihuoId;
    //用户编号
    private Integer userId;
    //退货时间
    private LocalDateTime tuihuoTime;
    //药品购入表编号
    private String purchaseId;

}

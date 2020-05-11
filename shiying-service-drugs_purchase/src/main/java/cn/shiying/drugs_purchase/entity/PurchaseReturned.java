package cn.shiying.drugs_purchase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PurchaseReturned  implements Serializable {

    @TableId(value = "tuihuo_id", type = IdType.AUTO)
    private Integer tuihuoId;
    private Integer userId;
    private String tuihuoTime;
    private String purchaseId;
}

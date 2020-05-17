package cn.shiying.drugs_purchase.entity;

import cn.shiying.drugs_purchase.entity.form.DrugsAndDetailed;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsPurchaseDetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 购入详情编号
     */
    @TableId(value = "pdid", type = IdType.ID_WORKER_STR)
    private Integer pdid;

    /**
     * 药品编号
     */
    private Integer drugsId;

    /**
     * 药品购入表编号
     */
    private String purchaseId;

    /**
     * 进货单价
     */
    private String pdMoney;

    /**
     * 进货数量
     */
    private String pdNum;

    @TableField(exist =false)
    List<DrugsAndDetailed> list;
}

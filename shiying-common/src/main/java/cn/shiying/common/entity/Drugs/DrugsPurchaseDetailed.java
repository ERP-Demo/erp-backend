package cn.shiying.common.entity.Drugs;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tyb
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsPurchaseDetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 购入详情编号
     */
    @TableId(value = "pdid", type = IdType.AUTO)
    private Integer pdid;

    /**
     * 药品编号
     */
    private Integer drugsId;

    /**
     * 购入药品编号
     */
    private String purchaseId;

    /**
     * 进货单价
     */
    private Integer pdMoney;

    /**
     * 进货数量
     */
    private Integer pdNum;


}

package cn.shiying.common.entity.drugs_storage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class drugs_detailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品编号，主键ID，主键，非空
     */
    @TableId(value = "drugs_id", type = IdType.AUTO)
    private Integer drugsId;

    /**
     * 药品名称，非空
     */
    private String drugsName;

    /**
     * 药品单价
     */
    private Double drugsPrice;

    /**
     * 药品规格
     */
    private String drugsNorms;

    /**
     * 药品用法
     */
    private String drugsUsage;

    /**
     * 药品用量
     */
    private String drugsDosage;

    /**
     * 禁忌
     */
    private String drugsTaboo;

    /**
     * 生产厂商
     */
    private String drugsProducer;

    /**
     * 批准文号
     */
    private String drugsApprovalNumber;

}

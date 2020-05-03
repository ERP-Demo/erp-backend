package cn.shiying.drugs_purchase.entity.vo;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.drugs_purchase.entity.DrugsSupplier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsSupplierVO extends DrugsSupplier {

    /**
     * 药品编号，主键ID，主键，非空
     */
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

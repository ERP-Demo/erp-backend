package cn.shiying.drug_model.entity.vo;

import cn.shiying.drug_model.entity.DrugModel;
import lombok.Data;

@Data
public class DrugModelVo extends DrugModel {
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

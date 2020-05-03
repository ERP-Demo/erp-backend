package cn.shiying.drug_model.entity;

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
 * @since 2020-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品编号
     */
    @TableId(value = "drug_model_id", type = IdType.AUTO)
    private Integer drugModelId;

    /**
     * 药品名称
     */
    private String drugModelName;

    /**
     * 药品单价（元）
     */
    private String drugModelPrice;

    /**
     * 药品数量
     */
    private String drugModelNum;

    /**
     * 药品规格
     */
    private Double drugModelSpecs;

    /**
     * 单位
     */
    private String drugModelCompany;

    /**
     * 药品拼英码
     */
    private String drugModelEnglishcode;

    /**
     * 使用建议
     */
    private String drugModelProposal;

    /**
     * 频次
     */
    private String drugModelFrequency;

    /**
     * 天数
     */
    private Integer drugModelDay;

    /**
     * 用量
     */
    private String drugModelConsumption;


}

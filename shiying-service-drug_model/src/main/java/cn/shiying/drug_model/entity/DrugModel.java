package cn.shiying.drug_model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
     * 药品模板编号
     */
    @TableId(value = "drug_model_id", type = IdType.AUTO)
    private Integer drugModelId;

    /**
     * 药品模板名称
     */
    private String drugModelName;

    /**
     * 药品模板简介
     */
    private String drugModelIntroduction;

    /**
     * 药品模板编码
     */
    private String drugModelCode;

    /**
     * 药品模板范围
     */
    private Integer drugModelRange;

    /**
     * 药品模板类型
     */
    private Integer drugModelType;



}

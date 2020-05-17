package cn.shiying.drug_model.entity;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsDrugModel implements Serializable {
    /**
     * 药品模板关联模板编号
     */
    private Integer drugModelId;

    /**
     * 药品模板关联药品编号
     */
    private Integer drugsId;
}

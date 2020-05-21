package cn.shiying.common.entity.Drugs.vo;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import lombok.Data;

@Data
public class DrugsVo extends DrugsDetailed {
    /**
     * 药瓶库存
     */
    private Integer pharmacyNum;

}

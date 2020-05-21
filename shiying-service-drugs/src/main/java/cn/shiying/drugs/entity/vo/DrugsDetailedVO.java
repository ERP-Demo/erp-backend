package cn.shiying.drugs.entity.vo;

import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import lombok.Data;

@Data
public class DrugsDetailedVO extends DrugsStorageReportsLoss {
    /**
     * 药品表
     */
    private String drugsName;

}

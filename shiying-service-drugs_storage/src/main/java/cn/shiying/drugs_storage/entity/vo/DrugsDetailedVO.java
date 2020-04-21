package cn.shiying.drugs_storage.entity.vo;

import cn.shiying.common.entity.drugs_storage.drugs_detailed;
import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
import lombok.Data;

@Data
public class DrugsDetailedVO extends DrugsStorageDetailedInfo {
    /**
     * 药品表
     */
    private String drugsName;
}

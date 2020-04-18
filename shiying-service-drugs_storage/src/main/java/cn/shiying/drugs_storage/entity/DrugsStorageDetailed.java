package cn.shiying.drugs_storage.entity;

import cn.shiying.common.entity.drugs_storage.drugs_detailed;
import cn.shiying.common.entity.drugs_storage.drugs_storage_detailed_info;
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
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsStorageDetailed implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药库id
     */
    @TableId(value = "pharmacy_id", type = IdType.AUTO)
    private Integer pharmacyId;

    /**
     * 药库名称
     */
    private String pharmacyName;

    /**
     * 药品表
     */
    private drugs_detailed dd;

    /**
     * 仓库药品表
     */
    private drugs_storage_detailed_info di;

}

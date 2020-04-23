package cn.shiying.drugs_storage.entity;

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
 * @since 2020-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsStorageDetailedInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药品编号
     */
    private Integer drugsId;

    /**
     * 仓库数量
     */
    private Integer pharmacyNum;

    /**
     * 库存预警
     */
    private Integer pharmacyWarning;

}

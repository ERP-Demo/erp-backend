package cn.shiying.prescription.entity;

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
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PrescriptionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 处方详情ID
     */
    @TableId(value = "pd_id", type = IdType.ID_WORKER_STR)
    private Integer pdId;

    private Integer prescriptionId;

    /**
     * 药品ID
     */
    private Integer drugsId;

    /**
     * 药品数量
     */
    private Integer drugsNum;

    /**
     * 使用方法
     */
    private String drugsUse;

    /**
     * 天数
     */
    private Integer drugsDay;

    /**
     * 用量
     */
    private String drugsUsenum;
    /**
     * 状态
     */
    private Integer status;


}

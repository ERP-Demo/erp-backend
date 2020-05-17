package cn.shiying.common.entity.patient_handle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tyb
 * @since 2020-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PatientHandle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 处置id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 处置名称
     */
    private String handleName;

    /**
     * 处置价格
     */
    private Double handlePrice;


}

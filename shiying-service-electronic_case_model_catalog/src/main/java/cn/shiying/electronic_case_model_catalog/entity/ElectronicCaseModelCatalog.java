package cn.shiying.electronic_case_model_catalog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 病例模版目录表

 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ElectronicCaseModelCatalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long parentId;

    private Integer level;

    private Integer type;

    private Integer status;

    private Long modelId;

    private Integer scope;

    private Long ownId;

    private LocalDateTime createTime;

    private String name;


}

package cn.shiying.test_model.entity;

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
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 化验模板的编号
     */
    @TableId(value = "test_model_id", type = IdType.AUTO)
    private Integer testModelId;

    /**
     * 化验模板名称
     */
    private String testModelName;

    /**
     * 化验模板简介
     */
    private String testModelIntroduction;

    /**
     * 化验模板的编码
     */
    private String testModelCode;

    /**
     * 化验模板的范围(0:个人,1:科室,2:全院)
     */
    private Integer testModelRange;


}

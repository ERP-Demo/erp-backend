package cn.shiying.common.entity.TestSynthesize;

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
 * @since 2020-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestSynthesize implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 综合化验的id
     */
    @TableId(value = "test_synthesize_id", type = IdType.AUTO)
    private Integer testSynthesizeId;

    private String testSynthesizeName;

    private double testSynthesizePrice;


}

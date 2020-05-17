package cn.shiying.test_model.entity.vo;

import cn.shiying.test_model.entity.TestModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TestModelVo extends TestModel {

    private static final long serialVersionUID = 1L;

    /**
     * 综合化验的id
     */
    private Integer testSynthesizeId;

    private String testSynthesizeName;

    private double testSynthesizePrice;

}

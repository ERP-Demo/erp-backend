package cn.shiying.test_model.entity.vo;

import lombok.Data;

@Data
public class TestModelVo {

    private static final long serialVersionUID = 1L;

    /**
     * 综合化验的id
     */
    private Integer testSynthesizeId;

    private String testSynthesizeName;

    private double testSynthesizePrice;

}

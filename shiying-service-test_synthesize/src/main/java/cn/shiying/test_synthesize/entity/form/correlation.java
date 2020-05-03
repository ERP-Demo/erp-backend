package cn.shiying.test_synthesize.entity.form;

import lombok.Data;

@Data
public class correlation {

    /**
     * 化验项目编号
     */
    private Integer testProjectsId;

    /**
     * 化验项目的简称
     */
    private String testAbbreviation;

    /**
     * 上限
     */
    private Double ceiling;

    /**
     * 下限
     */
    private Double floor;

    /**
     * 计量单位
     */
    private String unit;

}

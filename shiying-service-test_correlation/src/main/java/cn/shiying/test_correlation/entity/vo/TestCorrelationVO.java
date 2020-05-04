package cn.shiying.test_correlation.entity.vo;

import cn.shiying.common.entity.TestProjects.TestProjects;
import cn.shiying.test_correlation.entity.TestCorrelation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
public class TestCorrelationVO extends TestCorrelation {

    private TestProjects testProjects;

    /**
     * 化验名（子）
     */
    private String testName;
}

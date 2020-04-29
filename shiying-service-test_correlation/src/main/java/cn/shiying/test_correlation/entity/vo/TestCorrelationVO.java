package cn.shiying.test_correlation.entity.VO;

import cn.shiying.common.entity.TestProjects.TestProjects;
import cn.shiying.test_correlation.entity.TestCorrelation;
import lombok.Data;


@Data
public class TestCorrelationVO extends TestCorrelation {
    private TestProjects testProjects;
}

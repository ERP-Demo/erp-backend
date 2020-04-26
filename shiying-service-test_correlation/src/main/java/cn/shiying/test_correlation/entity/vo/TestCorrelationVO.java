package cn.shiying.test_correlation.entity.vo;

import cn.shiying.common.entity.TestProjects.TestProjects;
import cn.shiying.common.entity.TestSynthesize.TestSynthesize;
import cn.shiying.test_correlation.entity.TestCorrelation;

public class TestCorrelationVO extends TestCorrelation {
    //化验项目的具体内容
    private TestProjects testProjects;
    //化验项目的大体
    private TestSynthesize testSynthesize;
}

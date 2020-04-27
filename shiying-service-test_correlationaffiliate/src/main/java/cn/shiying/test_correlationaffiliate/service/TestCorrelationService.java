package cn.shiying.test_correlationaffiliate.service;

import cn.shiying.test_correlationaffiliate.entity.TestCorrelation;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-26
 */
public interface TestCorrelationService extends IService<TestCorrelation> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
//    子化验项目的下拉列表
    List<TestCorrelation> selectTestCorrelationId();
//    添加
    void addTestCorrelation(TestCorrelation testcorrelation);

}

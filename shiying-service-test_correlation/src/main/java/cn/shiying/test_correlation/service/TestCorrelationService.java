package cn.shiying.test_correlation.service;

import cn.shiying.test_correlation.entity.TestCorrelation;
import cn.shiying.test_correlation.entity.vo.TestCorrelationVO;
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
 * @since 2020-04-28
 */
public interface TestCorrelationService extends IService<TestCorrelation> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

}

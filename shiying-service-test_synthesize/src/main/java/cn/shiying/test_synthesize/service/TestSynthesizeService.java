package cn.shiying.test_synthesize.service;

import cn.shiying.test_synthesize.entity.TestSynthesize;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-23
 */
public interface TestSynthesizeService extends IService<TestSynthesize> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

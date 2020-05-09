package cn.shiying.test_model.service;

import cn.shiying.test_model.entity.TestModel;
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
 * @since 2020-05-05
 */
public interface TestModelService extends IService<TestModel> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
    //向关联表添加jilu
    void add(Integer testModelId, List<Integer> ids);
}

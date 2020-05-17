package cn.shiying.test_model.service;

import cn.shiying.test_model.entity.TestModel;
import cn.shiying.test_model.entity.vo.TestModelVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import feign.Param;

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
    //删除
    void del(@Param("ids") List<String> ids);
    //根据化验模板的编号查询
    List<TestModelVo> selectById(@Param("id") Integer id);
    TestModel selectbyid(Integer id);
    void delbyid(@Param("testModelId") Integer testModelId);
}

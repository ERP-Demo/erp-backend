package cn.shiying.requirements.service;

import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.TestSynthesizeAll;
import cn.shiying.requirements.entity.Vo.Requirements_Vo;
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
 * @since 2020-05-02
 */
public interface RequirementsService extends IService<Requirements> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

    void updatestate(Integer[] id);
    List<Requirements_Vo> All();
    List<TestSynthesizeAll> TestSynthesizeAll();
}

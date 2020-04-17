package cn.shiying.Test_projects.service;

import cn.shiying.Test_projects.entity.TestProjects;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
public interface TestProjectsService extends IService<TestProjects> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

package cn.shiying.drugs.service;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
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
 * @since 2020-04-16
 */
public interface DrugsDetailedService extends IService<DrugsDetailed> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

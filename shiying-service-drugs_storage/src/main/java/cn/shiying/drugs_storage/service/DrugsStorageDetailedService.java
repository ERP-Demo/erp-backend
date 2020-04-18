package cn.shiying.drugs_storage.service;

import cn.shiying.drugs_storage.entity.DrugsStorageDetailed;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
public interface DrugsStorageDetailedService extends IService<DrugsStorageDetailed> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

package cn.shiying.drugs_storage.service;

import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
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
 * @since 2020-04-18
 */
public interface DrugsStorageDetailedInfoService extends IService<DrugsStorageDetailedInfo> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

}

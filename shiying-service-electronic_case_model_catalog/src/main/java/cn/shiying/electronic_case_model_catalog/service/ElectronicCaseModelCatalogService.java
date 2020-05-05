package cn.shiying.electronic_case_model_catalog.service;

import cn.shiying.electronic_case_model_catalog.entity.ElectronicCaseModelCatalog;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import java.util.Map;

/**
 * <p>
 * 病例模版目录表
 服务类
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
public interface ElectronicCaseModelCatalogService extends IService<ElectronicCaseModelCatalog> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

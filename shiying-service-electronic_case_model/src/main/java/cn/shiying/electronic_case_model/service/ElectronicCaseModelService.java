package cn.shiying.electronic_case_model.service;

import cn.shiying.electronic_case_model.entity.ElectronicCaseModel;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import java.util.Map;

/**
 * <p>
 * 病历模版表 服务类
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
public interface ElectronicCaseModelService extends IService<ElectronicCaseModel> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

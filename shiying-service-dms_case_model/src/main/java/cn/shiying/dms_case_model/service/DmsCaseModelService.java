package cn.shiying.dms_case_model.service;

import cn.shiying.dms_case_model.entity.DmsCaseModel;
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
public interface DmsCaseModelService extends IService<DmsCaseModel> {

    /**
     * 描述：列出病历模板目录树
     */
    List<DmsCaseModelCatalogNode> listModelCatTree(Long ownId, Integer scope);
    /**
     * 描述：新增目录或模板
     */
    int createCatOrModel(DmsCaseModelOrCatalogParam dmsCaseModelOrCatalogParam);
    /**
     * 描述：删除目录或模板（id即可）
     */
    int deleteModelOrCat(Long id);

    /**
     * 描述：根据模板id更新模板（只更新模板表（除name））
     */
    int updateModel(Long modelId, DmsCaseModel newDmsCaseModel);

    /**
     * 描述：根据模板id更新模板和目录的名字
     */
    int updateName(Long id, String name);

    /**
     * 描述：根据模板id获取模板详细信息
     */
    DmsCaseModel getModelDetail(Long id);

    /**
     * 描述:根据staffId获取所有病历模板
     * <p>author: ma
     */
    DmsCaseModelListResult getAllStaffModel(Long staffId);
    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
}

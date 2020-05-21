package cn.shiying.electronic_case_template.service;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.common.entity.electronicCaseTemplate.Vo.ElectronicCaseTemplateVO;
import cn.shiying.electronic_case_template.entity.ElectronicCaseTemplate;
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
 * @since 2020-05-20
 */
public interface ElectronicCaseTemplateService extends IService<ElectronicCaseTemplate> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
     List<ElectronicCaseTemplateVO> allTemplate();
     void delDetailed(String tid);
     List<Icd> getIcds(String tid);
     void add(Integer tid, List<Integer> ids);
}

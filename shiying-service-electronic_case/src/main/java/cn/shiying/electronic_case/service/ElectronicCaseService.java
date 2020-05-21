package cn.shiying.electronic_case.service;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.common.entity.electronicCaseTemplate.Vo.ElectronicCaseTemplateVO;
import cn.shiying.electronic_case.entity.Case;
import cn.shiying.electronic_case.entity.ElectronicCase;
import cn.shiying.electronic_case.entity.vo.ElectronicAndDetailedVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
public interface ElectronicCaseService extends IService<ElectronicCase> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    void ElectronicCase(ElectronicCase electronicCase);

    ElectronicCase getRedis(ElectronicCase electronicCase);

    void saveCase(Case cas);

    List<String> getdetailed(Integer caseNo);

    //查询历史病历
    List<ElectronicAndDetailedVO> selectElectronic(Integer patientId);

    public List<Icd> topFive();

    void deleteByid(String id);
}

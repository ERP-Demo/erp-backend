package cn.shiying.patient_handle.service;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.common.entity.patient_handle.PatientHandle;
import cn.shiying.patient_handle.entity.form.HandleApplyForm;
import cn.shiying.common.entity.patient_handle.PatientHandleApplyDetailed;
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
 * @since 2020-05-11
 */
public interface PatientHandleService extends IService<PatientHandle> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

    void updatestate(Integer[] id);

    //根据患者编号查询患者名称
    PatientHandleApplyDetailed getPatientDeId(Integer patientId);

    PageUtils queryPagePatient(Map<String, Object> params);
    void runHandle(String username,Integer id);

    PageUtils queryPage2(Map<String, Object> params);
}

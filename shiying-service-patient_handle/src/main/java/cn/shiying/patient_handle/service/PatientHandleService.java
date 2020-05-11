package cn.shiying.patient_handle.service;

import cn.shiying.patient_handle.entity.PatientHandle;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
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
}

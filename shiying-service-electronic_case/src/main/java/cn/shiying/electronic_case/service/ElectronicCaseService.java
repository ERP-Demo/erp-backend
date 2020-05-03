package cn.shiying.electronic_case.service;

import cn.shiying.electronic_case.entity.ElectronicCase;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
public interface ElectronicCaseService extends IService<ElectronicCase> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
     void ElectronicCase(ElectronicCase electronicCase);
    ElectronicCase getRedis(ElectronicCase electronicCase);
}

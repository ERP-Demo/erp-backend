package cn.shiying.prescription.service;

import cn.shiying.prescription.entity.Prescription;
import cn.shiying.prescription.entity.Prescription_Vo;
import cn.shiying.prescription.entity.from.DrugsAndDetailed;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-30
 */
public interface PrescriptionService extends IService<Prescription> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
     Map<String, String> addDrugsAndDetailed(List<DrugsAndDetailed> drugsAndDetailed);
     void toVoid(List<Integer> ids);

    List<Prescription_Vo> PrescriptionVoByid(Integer[] id);

    void updatestate(Integer[] id);

    PageUtils queryPagePre(Map<String, Object> params);
}

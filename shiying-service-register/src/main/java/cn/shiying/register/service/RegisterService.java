package cn.shiying.register.service;

import cn.shiying.register.entity.Register;
import cn.shiying.register.entity.Vo.RegisterPatientVO;
import cn.shiying.register.entity.Vo.departmentVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-04-17
 */
public interface RegisterService extends IService<Register> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);

     List<departmentVo> departmentvo();

     List<RegisterPatientVO> list(List<Integer> id);

    PageUtils listPage(Map<String, Object> params);
}

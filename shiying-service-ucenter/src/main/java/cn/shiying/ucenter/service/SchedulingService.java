package cn.shiying.ucenter.service;

import cn.shiying.common.entity.scheduling.Scheduling;
import cn.shiying.common.entity.scheduling.Vo.SysUserVo;
import cn.shiying.common.entity.sys.SysUser;
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
 * @since 2020-04-27
 */
public interface SchedulingService extends IService<Scheduling> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
    List<SysUserVo> SysUserVo();
    List<SysUser> sysUser();
}

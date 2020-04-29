package cn.shiying.ucenter.mapper;

import cn.shiying.common.entity.scheduling.Scheduling;
import cn.shiying.common.entity.scheduling.Vo.SysUserVo;
import cn.shiying.common.entity.sys.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-27
 */
@Mapper
public interface SchedulingMapper extends BaseMapper<Scheduling> {
    List<SysUserVo> SysUserVo();
    List<SysUser> sysUser();
}

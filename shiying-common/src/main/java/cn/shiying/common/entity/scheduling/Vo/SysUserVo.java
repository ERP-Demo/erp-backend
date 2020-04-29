package cn.shiying.common.entity.scheduling.Vo;

import cn.shiying.common.entity.scheduling.Scheduling;
import cn.shiying.common.entity.sys.SysUser;
import lombok.Data;

@Data
public class SysUserVo extends Scheduling {
    private SysUser sysuser;
}

package cn.shiying.ucenter.service.impl;


import cn.shiying.common.entity.scheduling.Scheduling;
import cn.shiying.common.entity.scheduling.Vo.SysUserVo;
import cn.shiying.common.entity.sys.SysUser;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.ucenter.mapper.SchedulingMapper;
import cn.shiying.ucenter.service.SchedulingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-27
 */
@Service
public class SchedulingServiceImpl extends ServiceImpl<SchedulingMapper, Scheduling> implements SchedulingService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Scheduling> page=baseMapper.selectPage(new Query<Scheduling>(params).getPage(),
                new QueryWrapper<Scheduling>().lambda());
        return new PageUtils(page);
    }

    @Override
    public List<SysUserVo> SysUserVo() {
        return baseMapper.SysUserVo();
    }

    @Override
    public List<SysUser> sysUser() {
        return baseMapper.sysUser();
    }

}

package cn.shiying.register.service.impl;

import cn.shiying.register.entity.Register;
import cn.shiying.register.mapper.RegisterMapper;
import cn.shiying.register.service.RegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
@Service
public class RegisterServiceImpl extends ServiceImpl<RegisterMapper, Register> implements RegisterService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Register> page=baseMapper.selectPage(new Query<Register>(params).getPage(),
                new QueryWrapper<Register>().lambda());
        return new PageUtils(page);
    }

}

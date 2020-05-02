package cn.shiying.requirements.service.impl;

import cn.shiying.requirements.entity.Check;
import cn.shiying.requirements.mapper.CheckMapper;
import cn.shiying.requirements.service.CheckService;
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
 * @since 2020-05-02
 */
@Service
public class CheckServiceImpl extends ServiceImpl<CheckMapper, Check> implements CheckService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Check> page=baseMapper.selectPage(new Query<Check>(params).getPage(),
                new QueryWrapper<Check>().lambda());
        return new PageUtils(page);
    }

}

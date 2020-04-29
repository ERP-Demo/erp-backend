package cn.shiying.icd.service.impl;

import cn.shiying.icd.entity.Icd;
import cn.shiying.icd.mapper.IcdMapper;
import cn.shiying.icd.service.IcdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
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
public class IcdServiceImpl extends ServiceImpl<IcdMapper, Icd> implements IcdService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<Icd> page=baseMapper.selectPage(new Query<Icd>(params).getPage(),
                new QueryWrapper<Icd>().lambda());
        return new PageUtils(page);
    }

}

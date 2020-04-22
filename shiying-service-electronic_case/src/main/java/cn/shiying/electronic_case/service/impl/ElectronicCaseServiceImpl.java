package cn.shiying.electronic_case.service.impl;

import cn.shiying.electronic_case.entity.ElectronicCase;
import cn.shiying.electronic_case.entity.vo.ElectronicCaseVO;
import cn.shiying.electronic_case.mapper.ElectronicCaseMapper;
import cn.shiying.electronic_case.service.ElectronicCaseService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-04-17
 */
@Service
public class ElectronicCaseServiceImpl extends ServiceImpl<ElectronicCaseMapper, ElectronicCase> implements ElectronicCaseService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<ElectronicCase>(params).getPage();
        List<ElectronicCaseVO> list= baseMapper.listElectronicCaseVO(page,params);
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

}

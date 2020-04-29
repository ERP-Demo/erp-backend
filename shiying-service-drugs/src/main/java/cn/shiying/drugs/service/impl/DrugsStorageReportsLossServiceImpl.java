package cn.shiying.drugs.service.impl;


import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import cn.shiying.drugs.entity.vo.DrugsDetailedVO;
import cn.shiying.drugs.mapper.DrugsStorageReportsLossMapper;
import cn.shiying.drugs.service.DrugsStorageReportsLossService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-04-16
 */
@Service
public class DrugsStorageReportsLossServiceImpl extends ServiceImpl<DrugsStorageReportsLossMapper, DrugsStorageReportsLoss> implements DrugsStorageReportsLossService {


    @Override
    public List<DrugsStorageReportsLoss> selectDrugsId() {
        return baseMapper.selectDrugsId();
    }

    @Override
    public void addStorageReport(DrugsStorageReportsLoss drugsStorage) { baseMapper.addStorageReport(drugsStorage); }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<DrugsStorageReportsLoss>(params).getPage();
        List<DrugsDetailedVO> list= baseMapper.listDrugsDetailedVO(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }
}

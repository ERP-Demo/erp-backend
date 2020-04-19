package cn.shiying.drugs_storage.service.impl;

import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
import cn.shiying.drugs_storage.entity.vo.DrugsDetailedVO;
import cn.shiying.drugs_storage.mapper.DrugsStorageDetailedInfoMapper;
import cn.shiying.drugs_storage.service.DrugsStorageDetailedInfoService;
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
 * @since 2020-04-18
 */
@Service
public class DrugsStorageDetailedInfoServiceImpl extends ServiceImpl<DrugsStorageDetailedInfoMapper, DrugsStorageDetailedInfo> implements DrugsStorageDetailedInfoService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<DrugsStorageDetailedInfo>(params).getPage();
        List<DrugsDetailedVO> list= baseMapper.listDrugsDetailedVO();
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

}

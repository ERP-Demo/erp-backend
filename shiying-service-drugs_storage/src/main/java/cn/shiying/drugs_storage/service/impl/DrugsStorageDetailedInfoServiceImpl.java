package cn.shiying.drugs_storage.service.impl;

import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
import cn.shiying.drugs_storage.entity.vo.DrugsDetailedVO;
import cn.shiying.drugs_storage.mapper.DrugsStorageDetailedInfoMapper;
import cn.shiying.drugs_storage.service.DrugsStorageDetailedInfoService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<DrugsDetailedVO> list= baseMapper.listDrugsDetailedVO(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public void save(List<DrugsPurchaseDetailed> list){
        for (DrugsPurchaseDetailed detailed : list) {
            DrugsStorageDetailedInfo info = baseMapper.selectById(detailed.getDrugsId());
            DrugsStorageDetailedInfo detailedInfo = new DrugsStorageDetailedInfo();
            detailedInfo.setDrugsId(detailed.getDrugsId());
            if (info==null){
                detailedInfo.setPharmacyNum(detailed.getPdNum());
                detailedInfo.setPharmacyWarning(50);
                baseMapper.insert(detailedInfo);
            }else {
                detailedInfo.setPharmacyNum(detailed.getPdNum()+info.getPharmacyNum());
                baseMapper.updateById(detailedInfo);
            }
        }

//        storageService.saveOrUpdate().save(storage);
    }

}

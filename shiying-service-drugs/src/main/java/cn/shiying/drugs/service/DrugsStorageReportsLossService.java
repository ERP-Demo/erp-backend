package cn.shiying.drugs.service;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import com.baomidou.mybatisplus.extension.service.IService;
import feign.Param;

import java.util.List;
import java.util.Map;

public interface DrugsStorageReportsLossService extends IService<DrugsStorageReportsLoss> {

    List<DrugsStorageReportsLoss> selectDrugsId();

    void addStorageReport(DrugsStorageReportsLoss drugsStorage);

    PageUtils queryPage(Map<String, Object> params);

    List<DrugsDetailed> all();

    List<DrugsDetailed> queryByIds(@Param("ids") Integer[] ids);
}

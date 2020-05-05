package cn.shiying.drugs.mapper;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.drugs.entity.DrugsStorageReportsLoss;
import cn.shiying.drugs.entity.vo.DrugsDetailedVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DrugsStorageReportsLossMapper extends BaseMapper<DrugsStorageReportsLoss> {

    /*查询药品表（下拉列表）*/
    List<DrugsStorageReportsLoss> selectDrugsId();

    /*药品的报损添加*/
    void addStorageReport(DrugsStorageReportsLoss drugsStorage);

    /*药品和报损关联查询*/
    List<DrugsDetailedVO> listDrugsDetailedVO(Page<DrugsStorageReportsLoss> page, @Param("params") Map<String, Object> params);

    List<DrugsDetailed> all();
    List<DrugsDetailed> queryByIds(@Param("ids") Integer[] ids);
}

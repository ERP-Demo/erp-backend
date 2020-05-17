package cn.shiying.drug_model.mapper;

import cn.shiying.common.entity.patient.PatientDetailed;
import cn.shiying.drug_model.entity.DrugModel;
import cn.shiying.drug_model.entity.from.DrugModelFrom;
import cn.shiying.drug_model.entity.vo.DrugModelVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-05-01
 */
@Mapper
public interface DrugModelMapper extends BaseMapper<DrugModel> {
    void add(@Param("drugModelId") Integer drugModelId, @Param("ids") Integer ids);
    void del(@Param("ids") List<String> ids);
    List<DrugModelVo> selectById(@Param("id") Integer id);
    DrugModel selectbyid(@Param("id") Integer id);
    void delbyid(@Param("DrugModelId") Integer DrugModelId);
    List<DrugModel> queryByNameorRange(Page<DrugModel> page, @Param("params") Map<String, Object> params);
    List<DrugModelVo> selectDrug();
}

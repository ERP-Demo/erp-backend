package cn.shiying.drug_model.mapper;

import cn.shiying.drug_model.entity.DrugsDrugModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DrugsDrugModelMapper extends BaseMapper<DrugsDrugModel> {
    List<DrugsDrugModel> selectBydmId(Integer drugModelId);
}

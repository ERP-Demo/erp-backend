package cn.shiying.drug_model.mapper;

import cn.shiying.drug_model.entity.DrugModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;

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
}

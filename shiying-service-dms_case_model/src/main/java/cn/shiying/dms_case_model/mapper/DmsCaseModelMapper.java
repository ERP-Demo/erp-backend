package cn.shiying.dms_case_model.mapper;

import cn.shiying.dms_case_model.entity.DmsCaseModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 病历模版表 Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
@Mapper
public interface DmsCaseModelMapper extends BaseMapper<DmsCaseModel> {
    int countByExample(DmsCaseModelExample example);

    int deleteByExample(DmsCaseModelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DmsCaseModel record);

    int insertSelective(DmsCaseModel record);

    List<DmsCaseModel> selectByExample(DmsCaseModelExample example);

    DmsCaseModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DmsCaseModel record, @Param("example") DmsCaseModelExample example);

    int updateByExample(@Param("record") DmsCaseModel record, @Param("example") DmsCaseModelExample example);

    int updateByPrimaryKeySelective(DmsCaseModel record);

    int updateByPrimaryKey(DmsCaseModel record);

}

package cn.shiying.electronic_case.mapper;

import cn.shiying.electronic_case.entity.ElectronicCaseDetailed;
import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.electronic_case.entity.vo.ElectronicAndDetailedVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ElectronicCaseDetailedMapper extends BaseMapper<ElectronicCaseDetailed> {
    //查询历史病历
    List<ElectronicAndDetailedVO> selectElectronic(@Param("patientId") Integer patientId);
}

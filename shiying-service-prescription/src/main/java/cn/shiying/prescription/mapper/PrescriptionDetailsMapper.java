package cn.shiying.prescription.mapper;

import cn.shiying.prescription.entity.PrescriptionDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PrescriptionDetailsMapper {
    List<PrescriptionDetails> plist(@Param("id") String id);
}

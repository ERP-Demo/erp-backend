package cn.shiying.electronic_case_template.mapper;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.electronic_case_template.entity.ElectronicCaseTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-05-20
 */
@Mapper
public interface ElectronicCaseTemplateMapper extends BaseMapper<ElectronicCaseTemplate> {
    List<ElectronicCaseTemplate> getTemplate();
    List<Integer> getIds(@Param("tid") String tid);
    List<Icd> selById(List<Integer> ids);
    void delDetailed(@Param("tid") String tid);
    void addDetailed(@Param("tid") Integer tid, @Param("icdId") Integer icdId);
}

package cn.shiying.electronic_case_template.mapper;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.electronic_case_template.entity.ElectronicCaseTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
    List<Integer> getIds(Integer tid);
    List<Icd> selById(List<Integer> ids);
}

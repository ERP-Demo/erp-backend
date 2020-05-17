package cn.shiying.requirements.mapper;

import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.Vo.TestsheetChilrenVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestSheetChilrenMapper extends BaseMapper<Requirements> {

    List<TestsheetChilrenVO> list(Page<Requirements> page, Map<String, Object> params);

}

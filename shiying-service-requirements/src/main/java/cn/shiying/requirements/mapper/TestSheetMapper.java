package cn.shiying.requirements.mapper;

import cn.shiying.requirements.entity.LaboratoryList;
import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.Vo.ReqAndTestSheetVO;
import cn.shiying.requirements.entity.Vo.TestsheetChilrenVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestSheetMapper extends BaseMapper<Requirements> {

    public List<ReqAndTestSheetVO> listReqAndTestSheetVO(Page<Requirements> page, @Param("params") Map<String, Object> params);

    void addTestSheet(List<LaboratoryList> laboratoryLists);

    void updateStatus(@Param("testId") Integer testId);

    void TestSheetGo(Integer id);
}

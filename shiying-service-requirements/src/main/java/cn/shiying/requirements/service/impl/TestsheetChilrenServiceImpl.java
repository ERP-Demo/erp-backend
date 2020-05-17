package cn.shiying.requirements.service.impl;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.Vo.TestsheetChilrenVO;
import cn.shiying.requirements.mapper.TestSheetChilrenMapper;
import cn.shiying.requirements.mapper.TestSheetMapper;
import cn.shiying.requirements.service.TestsheetChilrenService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestsheetChilrenServiceImpl extends ServiceImpl<TestSheetChilrenMapper, Requirements> implements TestsheetChilrenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<Requirements>(params).getPage();
        List<TestsheetChilrenVO> list= baseMapper.list(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }
}
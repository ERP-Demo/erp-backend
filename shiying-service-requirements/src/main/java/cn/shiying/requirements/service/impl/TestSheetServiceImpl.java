package cn.shiying.requirements.service.impl;

import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.requirements.entity.LaboratoryList;
import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.Vo.ReqAndTestSheetVO;
import cn.shiying.requirements.entity.form.TestSheetForm;
import cn.shiying.requirements.mapper.RequirementsMapper;
import cn.shiying.requirements.mapper.TestSheetMapper;
import cn.shiying.requirements.service.TestSheetService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestSheetServiceImpl extends ServiceImpl<TestSheetMapper, Requirements> implements TestSheetService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<Requirements>(params).getPage();
        List<ReqAndTestSheetVO> list= baseMapper.listReqAndTestSheetVO(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public void addTestSheet(TestSheetForm testSheetForm) {
        List<LaboratoryList> as=new ArrayList<>();

        List<LaboratoryList> laboratoryLists=testSheetForm.getDataList();
        LaboratoryList lab;

        for (LaboratoryList l : laboratoryLists) {
            lab=new LaboratoryList();
            lab.setId(l.getId());
            lab.setTestProjectsId(l.getTestProjectsId());
            lab.setResult(l.getResult());
            as.add(lab);
        }

        Integer testId=testSheetForm.getTestAll();

        baseMapper.addTestSheet(laboratoryLists);
        baseMapper.updateStatus(testId);
    }

    @Override
    public void TestSheetGo(Integer id) {
        baseMapper.TestSheetGo(id);
    }

}

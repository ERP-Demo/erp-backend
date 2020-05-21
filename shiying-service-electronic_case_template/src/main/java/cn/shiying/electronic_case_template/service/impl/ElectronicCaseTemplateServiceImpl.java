package cn.shiying.electronic_case_template.service.impl;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.common.entity.electronicCaseTemplate.Vo.ElectronicCaseTemplateVO;
import cn.shiying.electronic_case_template.entity.ElectronicCaseTemplate;
import cn.shiying.electronic_case_template.mapper.ElectronicCaseTemplateMapper;
import cn.shiying.electronic_case_template.service.ElectronicCaseTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-05-20
 */
@Service
public class ElectronicCaseTemplateServiceImpl extends ServiceImpl<ElectronicCaseTemplateMapper, ElectronicCaseTemplate> implements ElectronicCaseTemplateService {
    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ElectronicCaseTemplate> page=baseMapper.selectPage(new Query<ElectronicCaseTemplate>(params).getPage(),
                new QueryWrapper<ElectronicCaseTemplate>().lambda());
        return new PageUtils(page);
    }

    public List<ElectronicCaseTemplateVO> allTemplate(){
        List<ElectronicCaseTemplateVO> list=new ArrayList<>();
        List<ElectronicCaseTemplate> templates=baseMapper.getTemplate();
        for (ElectronicCaseTemplate template : templates) {
            ElectronicCaseTemplateVO templateVO=new ElectronicCaseTemplateVO();
            List<Icd> icds=getIcds(template.getTid().toString());
            templateVO.setTid(template.getTid());
            templateVO.setComplain(template.getComplain());
            templateVO.setPatientSymptom(template.getPatientSymptom());
            templateVO.setMainIcd(template.getMainIcd());
            templateVO.setIcds(icds);
            list.add(templateVO);
        }
        return list;
    }

    @Override
    public void delDetailed(String tid) {
        baseMapper.delDetailed(tid);
    }

    @Override
    public List<Icd> getIcds(String tid) {
        List<Integer> ids = baseMapper.getIds(tid);
        if (ids.size()==0||ids==null)
            return null;
        return baseMapper.selById(ids);
    }

    @Override
    public void add(Integer tid, List<Integer> ids) {
        for (Integer id : ids) {
            baseMapper.addDetailed(tid,id);
        }
    }

}

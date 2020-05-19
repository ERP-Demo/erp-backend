package cn.shiying.electronic_case.service.impl;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.common.entity.electronicCaseTemplate.ElectronicCaseTemplate;
import cn.shiying.common.entity.electronicCaseTemplate.Vo.ElectronicCaseTemplateVO;
import cn.shiying.common.entity.token.JwtUser;
import cn.shiying.electronic_case.Util.RedisUtil;
import cn.shiying.electronic_case.entity.Case;
import cn.shiying.electronic_case.entity.ElectronicCase;
import cn.shiying.electronic_case.entity.ElectronicCaseDetailed;
import cn.shiying.electronic_case.entity.vo.ElectronicAndDetailedVO;
import cn.shiying.electronic_case.entity.vo.ElectronicCaseVO;
import cn.shiying.electronic_case.mapper.ElectronicCaseDetailedMapper;
import cn.shiying.electronic_case.mapper.ElectronicCaseMapper;
import cn.shiying.electronic_case.service.ElectronicCaseService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
@Service
public class ElectronicCaseServiceImpl extends ServiceImpl<ElectronicCaseMapper, ElectronicCase> implements ElectronicCaseService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ElectronicCaseDetailedMapper electronicCaseDetailedMapper;

    @Autowired
    ElectronicCaseMapper electronicCaseMapper;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page = new Query<ElectronicCase>(params).getPage();
        List<ElectronicCaseVO> list = baseMapper.listElectronicCaseVO(params);
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public void ElectronicCase(ElectronicCase electronicCase) {
        String id = Integer.toString(electronicCase.getPatientId());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(electronicCase);
            redisTemplate.boundValueOps(id).set(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ElectronicCase getRedis(ElectronicCase electronicCase) {
        String id = Integer.toString(electronicCase.getPatientId());
        String students = redisTemplate.boundValueOps(id).get();
        ElectronicCase electronicCase1 = JSON.parseObject(students, ElectronicCase.class);
        return electronicCase1;
    }

    @Override
    public void saveCase(Case cas) {
        ElectronicCase aCase = cas.getElectronicCase();
        String id = aCase.getRegisterId();
        aCase.setUid(getUser().getUid());
        baseMapper.insert(aCase);
        List<Integer> icdId = cas.getIcdId();
        for (Integer icd : icdId) {
            ElectronicCaseDetailed electronicCaseDetailed = new ElectronicCaseDetailed();
            electronicCaseDetailed.setCaseNo(aCase.getCaseNo());
            electronicCaseDetailed.setIcdId(icd);
            electronicCaseDetailedMapper.insert(electronicCaseDetailed);
        }
        redisTemplate.delete("Case:"+id);
    }

    @Override
    public List<String> getdetailed(Integer caseNo) {
        List<ElectronicCaseDetailed> electronicCaseDetaileds = electronicCaseDetailedMapper.selectList(new QueryWrapper<ElectronicCaseDetailed>().eq("case_no", caseNo));
        List<String> icds=new ArrayList<>();
        for (ElectronicCaseDetailed electronicCaseDetailed : electronicCaseDetaileds) {
            icds.add(electronicCaseDetailed.getIcdId()+"");
        }
        return icds;
    }

    public JwtUser getUser() {
        Map<String, Object> map = (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user = new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        user.setDepartmentId((List<Integer>) map.get("departmentId"));
        return user;
    }

    //查询历史病历
    @Override
    public List<ElectronicAndDetailedVO> selectElectronic(Integer patientId) {
        return electronicCaseDetailedMapper.selectElectronic(patientId);
    }

    public List<Icd> topFive(){
        return electronicCaseDetailedMapper.selById(electronicCaseDetailedMapper.getIds(getUser().getUid()));
    }

    public List<ElectronicCaseTemplateVO> allTemplate(){
        List<ElectronicCaseTemplateVO> list=new ArrayList<>();
        List<ElectronicCaseTemplate> templates=electronicCaseMapper.getTemplate();
        for (ElectronicCaseTemplate template : templates) {
            ElectronicCaseTemplateVO templateVO=new ElectronicCaseTemplateVO();
            List<Icd> icds=electronicCaseDetailedMapper.selById(electronicCaseMapper.getIds(template.getTid()));
            templateVO.setTid(template.getTid());
            templateVO.setComplain(template.getComplain());
            templateVO.setPatientSymptom(template.getPatientSymptom());
            templateVO.setIcds(icds);
            list.add(templateVO);
        }
        return list;
    }
}

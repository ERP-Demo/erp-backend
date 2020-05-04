package cn.shiying.test_synthesize.service.impl;

import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.token.JwtUser;
import cn.shiying.test_synthesize.entity.TestCorrelation;
import cn.shiying.test_synthesize.entity.TestSynthesize;
import cn.shiying.test_synthesize.entity.form.SynthesizeAndProjects;
import cn.shiying.test_synthesize.entity.form.correlation;
import cn.shiying.test_synthesize.mapper.TestSynthesizeMapper;
import cn.shiying.test_synthesize.service.TestSynthesizeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.shiying.common.utils.Query;
import cn.shiying.common.utils.PageUtils;
import org.springframework.security.core.context.SecurityContextHolder;
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
 * @since 2020-04-23
 */
@Service
public class TestSynthesizeServiceImpl extends ServiceImpl<TestSynthesizeMapper, TestSynthesize> implements TestSynthesizeService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<TestSynthesize>(params).getPage();
        List<TestSynthesize> list= baseMapper.SynthesizeList(page,params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    public JwtUser getUser(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user=new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        user.setDepartmentId((List<Integer>) map.get("departmentId"));
        return user;
    }

    /**
     * 添加化验
     * @param synthesizeAndProjects 值
     */
    @Override
    public void addSynthesizeAndProjects(SynthesizeAndProjects synthesizeAndProjects) {
        System.out.println("进入");
        //添加化验表
        TestSynthesize synthesize = new TestSynthesize();
        synthesize.setTestSynthesizeName(synthesizeAndProjects.getSynthesizeName());
        synthesize.setTestSynthesizePrice(synthesizeAndProjects.getSynthesizPrice());
        System.out.println("化验表:"+synthesize);
        baseMapper.insert(synthesize);
        System.out.println("化验编号："+synthesize.getTestSynthesizeId());

        //添加化验项目表
        List<TestCorrelation> co=new ArrayList<>();
        TestCorrelation correlation;

        //循环getDetailed数据
        List<correlation> CorrelationList=synthesizeAndProjects.getDetailed();

        for (cn.shiying.test_synthesize.entity.form.correlation c : CorrelationList) {
            correlation = new TestCorrelation();
            correlation.setTestSynthesizeId(synthesize.getTestSynthesizeId());//化验编号
            correlation.setTestProjectsId(c.getTestProjectsId());//化验项目编号
            correlation.setCeiling(c.getCeiling());//上限
            correlation.setFloor(c.getFloor());//下限
            correlation.setUnit(c.getUnit());//单位
            correlation.setUid(getUser().getUid());//操作人
            co.add(correlation);
            System.out.println("化验项目表:"+correlation);
        }
        baseMapper.addTestCorrelation(co);



        System.out.println("数据："+synthesizeAndProjects);
    }

}

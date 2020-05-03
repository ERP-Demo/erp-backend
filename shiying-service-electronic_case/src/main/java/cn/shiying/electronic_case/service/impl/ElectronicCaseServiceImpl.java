package cn.shiying.electronic_case.service.impl;

import cn.shiying.electronic_case.Util.RedisUtil;
import cn.shiying.electronic_case.entity.ElectronicCase;
import cn.shiying.electronic_case.entity.vo.ElectronicCaseVO;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
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
    RedisTemplate<String,String> redisTemplate;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page page=new Query<ElectronicCase>(params).getPage();
        List<ElectronicCaseVO> list= baseMapper.listElectronicCaseVO(params);
        System.out.println(list);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public void ElectronicCase(ElectronicCase electronicCase) {
       String id=Integer.toString(electronicCase.getPatientId());
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(electronicCase);
            redisTemplate.boundValueOps(id).set(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ElectronicCase getRedis(ElectronicCase electronicCase) {
        String id=Integer.toString(electronicCase.getPatientId());
        String students = redisTemplate.boundValueOps(id).get();
        ElectronicCase electronicCase1=JSON.parseObject(students,ElectronicCase.class);
        System.out.println("取出来数据"+electronicCase1);
        return electronicCase1;
    }

}

package cn.shiying.electronic_case.controller;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.common.entity.token.JwtUser;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.electronic_case.client.IcdClient;
import cn.shiying.electronic_case.entity.Case;
import cn.shiying.electronic_case.entity.vo.CaseVO;
import cn.shiying.electronic_case.entity.vo.ElectronicAndDetailedVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.electronic_case.entity.ElectronicCase;
import cn.shiying.electronic_case.service.ElectronicCaseService;
import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.validator.ValidatorUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tyb
 * @since 2020-04-17
 */
@RestController
@RequestMapping("case")
public class ElectronicCaseController {
    @Autowired
    private ElectronicCaseService caseService;
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Autowired
    IcdClient icdClient;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('electronic_case:case:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = caseService.queryPage(params);
        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('electronic_case:case:info')")
    public Result info(@PathVariable("id") String id) {
        ElectronicCase case1 = caseService.getOne(new QueryWrapper<ElectronicCase>().eq("register_id", id));
        if (case1 != null) {
            List<String> ids = caseService.getdetailed(case1.getCaseNo());
            Result result = icdClient.icds(ids);
            if ((Integer) result.get("code") != 200) return Result.error(ErrorEnum.LOAD_TIME_LANG);
            CaseVO caseVO = new CaseVO();
            caseVO.setElectronicCase(case1);
            caseVO.setIcds((List<Icd>) result.get("icds"));
            System.out.println("数据" + caseVO);
            return Result.ok().put("case", caseVO);
        }
        CaseVO caseVO1 = new CaseVO();
        ElectronicCase e=caseService.getById(id);
        return Result.ok().put("case", caseVO1).put("e",e);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('electronic_case:case:save')")
    public Result save(@RequestBody Case cas) {
        caseService.addCase(cas);
        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('electronic_case:case:update')")
    public Result update(@RequestBody ElectronicCase cas){
        ValidatorUtils.validateEntity(cas);
        caseService.updateById(cas);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('electronic_case:case:delete')")
    public Result delete(@RequestBody String[] ids){
        caseService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    public JwtUser getUser(){
        Map<String,Object> map= (Map<String, Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JwtUser user=new JwtUser();
        user.setUid((Integer) map.get("uid"));
        user.setUsername((String) map.get("username"));
        user.setDepartmentId((List<Integer>) map.get("departmentId"));
        return user;
    }
    @PostMapping("/saveRidis")
    public Result saveRidis(@RequestBody ElectronicCase cas) {
        caseService.ElectronicCase(cas);
        return Result.ok();
    }
    @PostMapping("/getRidis")
    public Result getRidis(@RequestBody ElectronicCase cas) {
        ElectronicCase redis = caseService.getRedis(cas);
        return Result.ok().put("list",redis);
    }

    //查询历史病历
    @GetMapping("/selectElectronic/{patientId}")
    public Result selectElectronic(@PathVariable("patientId") Integer patientId){
        List<ElectronicAndDetailedVO> list = caseService.selectElectronic(patientId);
        for (ElectronicAndDetailedVO electronicAndDetailedVO : list) {
            System.out.println(electronicAndDetailedVO);
        }
        return Result.ok().put("list",list);
    }

    @GetMapping("/topFive")
    public Result topFive(){
        return Result.ok().put("list",caseService.topFive());
    }
}

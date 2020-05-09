package cn.shiying.electronic_case.controller;

import cn.shiying.common.entity.token.JwtUser;
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
    public Result info(@PathVariable("id") String id){
       ElectronicCase case1 = caseService.getById(id);

        return Result.ok().put("case", case1);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('electronic_case:case:save')")
    public Result save(@RequestBody ElectronicCase cas){
        String id=Integer.toString(cas.getPatientId());
        ValidatorUtils.validateEntity(cas);
        cas.setUid(getUser().getUid());
        caseService.save(cas);
        redisTemplate.delete(id);
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


}

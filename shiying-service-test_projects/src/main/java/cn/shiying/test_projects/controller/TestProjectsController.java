package cn.shiying.test_projects.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.test_projects.entity.TestProjects;
import cn.shiying.test_projects.service.TestProjectsService;
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
 * @since 2020-04-29
 */
@RestController
@RequestMapping("projects")
public class TestProjectsController {
    @Autowired
    private TestProjectsService projectsService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('test_projects:projects:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = projectsService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @PreAuthorize("hasAuthority('test_projects:projects:info')")
    public Result info(@PathVariable("id") String id){
       TestProjects projects = projectsService.getById(id);

        return Result.ok().put("projects", projects);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test_projects:projects:save')")
    public Result save(@RequestBody TestProjects projects){
        ValidatorUtils.validateEntity(projects);
        projectsService.save(projects);

        return Result.ok();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('test_projects:projects:update')")
    public Result update(@RequestBody TestProjects projects){
        ValidatorUtils.validateEntity(projects);
        projectsService.updateById(projects);
        return Result.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('test_projects:projects:delete')")
    public Result delete(@RequestBody String[] ids){
        projectsService.removeByIds(Arrays.asList(ids));

        return Result.ok();
    }
    @RequestMapping("/All")
    public Result all(){
        List<TestProjects> boxTestProjects = projectsService.boxTestProjects();
        for (TestProjects TestProjects : boxTestProjects) {
            System.out.println(TestProjects);
        }
        return Result.ok().put("list",boxTestProjects);
    }
}

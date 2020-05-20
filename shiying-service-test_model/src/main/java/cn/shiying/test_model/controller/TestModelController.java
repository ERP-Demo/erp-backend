package cn.shiying.test_model.controller;

import cn.shiying.test_model.entity.from.TestModelFrom;
import cn.shiying.test_model.entity.vo.TestModelVo;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.shiying.test_model.entity.TestModel;
import cn.shiying.test_model.service.TestModelService;
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
 * @since 2020-05-05
 */
@RestController
@RequestMapping("model")
public class TestModelController {
    @Autowired
    private TestModelService modelService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('test_model:model:list')")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = modelService.queryPage(params);
        System.out.println(page);
        return Result.ok().put("page", page);
    }




    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('test_model:model:save')")
    public Result save(@RequestBody TestModelFrom from){
        //验证工具
        ValidatorUtils.validateEntity(from);
        System.out.println("11111111111111111111111111111111111111111111111");
        TestModel t=from.getTestModel();
        System.out.println("拿到了++++++++++++++++++++"+t);
        System.out.println("222222222222222222222222222222222222222222222222");
        modelService.save(t);
        System.out.println("333333333333333333333333333333333333333333333333");
        System.out.println(t.getTestModelId());
        from.setTestModelId(from.getTestModelId());
        System.out.println(t.getTestModelId()+"++++++++++++++保存++++++++++++++++"+from.getIds());
        modelService.add(t.getTestModelId(),from.getIds());
        return Result.ok();
    }
    /**
     * 保存
     */



    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('test_model:model:delete')")
    public Result delete(@RequestBody String[] ids){
        modelService.removeByIds(Arrays.asList(ids));
        System.out.println(Arrays.asList(ids));
        modelService.del(Arrays.asList(ids));
        return Result.ok();
    }
    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
        List<TestModelVo> drugModelVos = modelService.selectById(id);
        for (TestModelVo drugModelVo : drugModelVos) {
            System.out.println(drugModelVo);
        }
//        double allprice=0.0;
//        for (TestModelVo test:drugModelVos){
//            allprice+=test.getTestSynthesizePrice();
//        }
//        String price=String.valueOf(allprice);

        TestModel selectbyid = modelService.selectbyid(id);

        List<TestModel> t=new ArrayList<>();
        t.add(selectbyid);
        return Result.ok().put("list",drugModelVos).put("mode",t);
    }
    /**
     * 修改
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('test_model:model:update')")
    public Result update(@RequestBody TestModelFrom from){
        ValidatorUtils.validateEntity(from);
        System.out.println(from);
        TestModel m=from.getTestModel();
        m.setTestModelId(m.getTestModelId());
        System.out.println("m:"+m);
        System.out.println("id:"+m.getTestModelId());
        modelService.updateById(m);
        modelService.delbyid(m.getTestModelId());
        m.setTestModelId(m.getTestModelId());
        System.out.println(m.getTestModelId()+"++++++++++++++++++++++++++++++"+from.getIds());
        modelService.add(m.getTestModelId(),from.getIds());
        return Result.ok();
    }

    @RequestMapping("/byid/{id}")
    public Result byid(@PathVariable Integer id){
        System.out.println(id);
        TestModel selectbyid = modelService.selectbyid(id);
        System.out.println(selectbyid);
        return Result.ok().put("mode",selectbyid);
    }

}

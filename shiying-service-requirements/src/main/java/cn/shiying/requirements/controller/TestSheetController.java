package cn.shiying.requirements.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.requirements.entity.LaboratoryList;
import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.form.TestSheetForm;
import cn.shiying.requirements.service.RequirementsService;
import cn.shiying.requirements.service.TestSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("testsheet")
public class TestSheetController {

    @Autowired
    private TestSheetService testSheetService;

    @GetMapping("/selectTestSheet")
    public Result selectTestSheet(@RequestParam Map<String, Object> params){
        PageUtils page = testSheetService.queryPage(params);
        return Result.ok().put("page", page);
    }

    /**
     * 化验单
     */
    @PostMapping("/addTestSheet")
    public Result addTestSheet(@RequestBody TestSheetForm testSheetForm){
        testSheetService.addTestSheet(testSheetForm);
        return Result.ok();
    }

}

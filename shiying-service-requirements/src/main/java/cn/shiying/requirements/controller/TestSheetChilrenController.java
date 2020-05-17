package cn.shiying.requirements.controller;

import cn.shiying.common.dto.Result;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.requirements.service.TestSheetService;
import cn.shiying.requirements.service.TestsheetChilrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("testsheetChilren")
public class TestSheetChilrenController {

    @Autowired
    private TestsheetChilrenService testsheetChilrenService;

    @GetMapping("/selectTestSheetChilren")
    public Result selectTestSheetChilren(@RequestParam Map<String, Object> params){
        PageUtils page = testsheetChilrenService.queryPage(params);
        return Result.ok().put("page", page);
    }

}

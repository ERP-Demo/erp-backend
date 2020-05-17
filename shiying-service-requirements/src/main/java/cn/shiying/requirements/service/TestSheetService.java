package cn.shiying.requirements.service;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.requirements.entity.LaboratoryList;
import cn.shiying.requirements.entity.Requirements;
import cn.shiying.requirements.entity.form.TestSheetForm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface TestSheetService extends IService<Requirements> {

    PageUtils queryPage(Map<String, Object> params);

    void addTestSheet(TestSheetForm testSheetForm);
}

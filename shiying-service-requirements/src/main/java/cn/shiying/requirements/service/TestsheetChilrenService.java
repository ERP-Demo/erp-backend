package cn.shiying.requirements.service;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.requirements.entity.Requirements;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface TestsheetChilrenService extends IService<Requirements> {

    PageUtils queryPage(Map<String, Object> params);

}

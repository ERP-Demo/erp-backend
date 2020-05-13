package cn.shiying.drug_model.service;

import cn.shiying.drug_model.entity.DrugModel;
import cn.shiying.drug_model.entity.from.DrugModelFrom;
import cn.shiying.drug_model.entity.vo.DrugModelVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;
import feign.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-05-01
 */
public interface DrugModelService extends IService<DrugModel> {

    /**
     * 分页查询
     * @param params
     * @return
     */
     PageUtils queryPage(Map<String, Object> params);
    //向关联表添加jilu
    void add(Integer drugModelId, List<Integer> ids);
    //删除
    void del(@Param("ids") List<String> ids);
    //根据药品模板的编号查询
    List<DrugModelVo> selectById(@Param("id") Integer id);

    DrugModel selectbyid(Integer id);

    void delbyid(@Param("DrugModelId") Integer DrugModelId);
}

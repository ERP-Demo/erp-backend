package cn.shiying.drugs_purchase.service;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugs_purchase.entity.form.DrugsAndDetailed;
import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.shiying.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tyb
 * @since 2020-04-22
 */
public interface DrugsPurchaseService extends IService<DrugsPurchase> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
    List<DrugsPurchaseDetailedVO> selectBypid(String pid);
    List<SupplierDetailed> selectSname();
    List<DrugsDetailed> selectDname();
    int insertPurchase(DrugsPurchase drugsPurchase);
    int insertDetailed(DrugsPurchaseDetailed drugsPurchaseDetailed);
    void addSupplierAndDrugs(DrugsAndDetailed drugsAndDetailed);

}

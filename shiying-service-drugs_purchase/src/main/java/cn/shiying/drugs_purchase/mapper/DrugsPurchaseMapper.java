package cn.shiying.drugs_purchase.mapper;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import cn.shiying.drugs_purchase.entity.vo.DrugsPurchaseDetailedVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-22
 */
@Mapper
public interface DrugsPurchaseMapper extends BaseMapper<DrugsPurchase> {
    List<DrugsPurchaseDetailedVO> selectBypid(String pid);
    List<SupplierDetailed> selectSname();
    List<DrugsDetailed> selectDname();
    int insertPurchase(DrugsPurchase drugsPurchase);
    int insertDetailed(DrugsPurchaseDetailed drugsPurchaseDetailed);

    //添加药品购入表
    void addDrugsPurchase(DrugsPurchase drugsPurchase);
    //添加药品购入详细表
    void addDrugsPurchaseDetailed(List<DrugsPurchaseDetailed> drugsPurchaseDetailed);

}

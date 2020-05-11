package cn.shiying.drugs_purchase.mapper;

import cn.shiying.drugs_purchase.entity.DrugsPurchase;
import cn.shiying.drugs_purchase.entity.PurchaseReturned;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseReturnedMapper extends BaseMapper<PurchaseReturned> {
}

package cn.shiying.supplier.entity.vo;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.supplier.entity.SupplierDetailed;
import lombok.Data;

@Data
public class Drugs_detailedVo extends SupplierDetailed {
    //药品
    private DrugsDetailed drugs_detailed;
}

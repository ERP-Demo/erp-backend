package cn.shiying.drugsSupplier.entity.vo;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.supplier.SupplierDetailed;
import cn.shiying.drugsSupplier.entity.DrugsSupplier;
import lombok.Data;

@Data
public class DrugsSupplierVO extends DrugsSupplier {
    //药品
    private DrugsDetailed drugsDetailed;

    private SupplierDetailed supplierDetailed;
}

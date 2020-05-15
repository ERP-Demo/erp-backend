package cn.shiying.drugs_purchase.entity.vo;

import cn.shiying.common.entity.Drugs.DrugsPurchaseDetailed;
import lombok.Data;

@Data
public class DrugsPurchaseDetailedVO extends DrugsPurchaseDetailed {
    private String drugsName;
    private Integer tuihuoNum;
    private Integer freezeNum;
}

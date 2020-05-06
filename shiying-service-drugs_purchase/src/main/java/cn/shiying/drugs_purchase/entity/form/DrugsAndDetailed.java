package cn.shiying.drugs_purchase.entity.form;

import lombok.Data;

import java.util.List;

@Data
public class DrugsAndDetailed {
    private String purchaseId;

    private Integer supplierId;

    private double payPrice;

    private List<Drugs> detailed;

}

package cn.shiying.drugs_purchase.entity.form;

import lombok.Data;

import java.util.List;

@Data
public class DrugsAndDetailed {
    /**
     * 供应商的编号
     */
    private Integer supplierId;

    private double payPrice;

    private List<Drugs> detailed;

}

package cn.shiying.drugs_purchase.entity.form;


import lombok.Data;

@Data
public class Drugs {

    /**
     * 药品编号
     */
    private Integer drugsId;

    /**
     * 药品名称
     */
    private String drugsName;


    /**
     * 进货单价
     */
    private Integer pdMoney;

    /**
     * 进货数量
     */
    private Integer pdNum;

    public Drugs(Integer drugsId, String drugsName, Integer pdMoney, Integer pdNum) {
        this.drugsId = drugsId;
        this.drugsName = drugsName;
        this.pdMoney = pdMoney;
        this.pdNum = pdNum;
    }
}

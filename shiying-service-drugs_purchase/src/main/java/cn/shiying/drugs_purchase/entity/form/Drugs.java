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
    private Integer price;

    /**
     * 进货数量
     */
    private Integer num;

    public Drugs(Integer drugsId, String drugsName, Integer price, Integer num) {
        this.drugsId = drugsId;
        this.drugsName = drugsName;
        this.price = price;
        this.num = num;
    }
}

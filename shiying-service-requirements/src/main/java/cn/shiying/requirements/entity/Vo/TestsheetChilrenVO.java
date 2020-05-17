package cn.shiying.requirements.entity.Vo;


import cn.shiying.requirements.entity.Requirements;
import lombok.Data;

@Data
public class TestsheetChilrenVO extends Requirements {

    //id
    private String testProjectsId;

    //化验名（子）
    private String testName;

    //下限
    private Double floor;

    //上限
    private Double ceiling;

    //计量单位
    private String unit;
}

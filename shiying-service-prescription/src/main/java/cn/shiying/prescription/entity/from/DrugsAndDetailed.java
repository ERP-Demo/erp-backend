package cn.shiying.prescription.entity.from;

import cn.shiying.prescription.entity.PrescriptionDetails;
import lombok.Data;

import java.util.List;

@Data
public class DrugsAndDetailed {
    /**
     * 处方名
     */
    private String prescriptionName;
    private Integer rowId;
    private List<PrescriptionDetails> druglist;
    private String drugsName;

    private Integer pdId;

    /**
     * 药品数量
     */
    private Integer drugsNum;

    /**
     * 使用方法
     */
    private String drugsUse;

    /**
     * 天数
     */
    private Integer drugsDay;

    /**
     * 用量
     */
    private String drugsUsenum;
    /**
     * 状态
     */
    private Integer status;
}

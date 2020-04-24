package cn.shiying.drugs.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DrugsStorageReportsLoss implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药库报损id
     */
    private Integer reportedLossId;

    public DrugsStorageReportsLoss(Integer reportedLossId, Integer drugsId, Integer reportedLossCount, String reportedLossWhy, String reportedLossNote, String reportedLossOperationOf) {
        this.reportedLossId = reportedLossId;
        this.drugsId = drugsId;
        this.reportedLossCount = reportedLossCount;
        this.reportedLossWhy = reportedLossWhy;
        this.reportedLossNote = reportedLossNote;
        this.reportedLossOperationOf = reportedLossOperationOf;
    }

    public DrugsStorageReportsLoss() {
    }

    /**
     * 药品编号
     */
    private Integer drugsId;

    /**
     * 数量
     */
    private Integer reportedLossCount;

    /**
     * 报损原因
     */
    private String reportedLossWhy;

    /**
     * 备注
     */
    private String reportedLossNote;

    /**
     * 操作人
     */
    private String reportedLossOperationOf;

}

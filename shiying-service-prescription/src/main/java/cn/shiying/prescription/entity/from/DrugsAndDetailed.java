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
}

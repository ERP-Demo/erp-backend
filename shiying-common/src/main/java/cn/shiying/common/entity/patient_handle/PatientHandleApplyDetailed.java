package cn.shiying.common.entity.patient_handle;

import cn.shiying.common.entity.patient.PatientDetailed;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class PatientHandleApplyDetailed {
    /**
     * 处置开立id
     */
    private Integer applyId;

    /**
     * 处置id
     */
    private Integer handleId;

    /**
     * 状态
     */
    private Integer status;


    @TableField(exist = false)
    private PatientHandle patientHandle;

    @TableField(exist = false)
    private PatientHandleApply patientHandleApply;
    @TableField(exist = false)
    private PatientDetailed patientDetailed;

}

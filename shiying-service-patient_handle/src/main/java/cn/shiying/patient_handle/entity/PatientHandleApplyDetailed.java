package cn.shiying.patient_handle.entity;

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

}
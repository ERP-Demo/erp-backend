package cn.shiying.patient_handle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PatientHandleApply{
    /**
    * 详细表id
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
    * 就诊号
    */
    private String registerId;
    /**
    * 患者id
    */
    private Integer patientId;

}
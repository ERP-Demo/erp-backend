package cn.shiying.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author tyb
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Requirements implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编码
     */
    @TableId(value = "testSynthesize_id")
    private Integer testSynthesizeId;
    /**
     * 医生id
     */
    private Integer uid;
    /**
     * 患者id
     */
    private Integer patientId;
    /**
     * 就诊号
     */
    private String registerId;

    /**
     * 目的
     */
    private String purpose;

    /**
     * 要求
     */
    private String requirements;

    /**
     * 临床印象
     */
    @TableField("Clinical_impression")
    private String clinicalImpression;

    /**
     * 临床诊断
     */
    @TableField("Clinical_diagnosis")
    private String clinicalDiagnosis;

    /**
     * 检查部位
     */
    @TableField("Check_the")
    private String checkThe;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;


}

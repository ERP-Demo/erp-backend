package cn.shiying.requirements.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Check implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目编码
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

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


}

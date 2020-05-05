package cn.shiying.electronic_case_model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 病历模版表
 * </p>
 *
 * @author tyb
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ElectronicCaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String chiefComplaint;

    private String historyOfPresentIllness;

    private String historyOfTreatment;

    private String pastHistory;

    private String allergies;

    private String healthCheckup;

    private String priliminaryDiseIdList;

    private String priliminaryDiseStrList;

    private String name;

    private Integer status;


}

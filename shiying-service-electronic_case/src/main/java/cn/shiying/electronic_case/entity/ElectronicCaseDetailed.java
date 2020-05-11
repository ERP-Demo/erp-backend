package cn.shiying.electronic_case.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ElectronicCaseDetailed {

    @TableId(value = "case_no", type = IdType.AUTO)
    private Integer id;
    private Integer caseNo;
    private Integer icdId;
}

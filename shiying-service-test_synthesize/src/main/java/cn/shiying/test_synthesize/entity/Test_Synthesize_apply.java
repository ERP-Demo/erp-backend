package cn.shiying.test_synthesize.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Test_Synthesize_apply {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 就诊号
     */
    private String registerId;
    /**
     * 患者id
     */
    private int patientId;
}

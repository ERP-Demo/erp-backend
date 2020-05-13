package cn.shiying.test_synthesize.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Test_Synthesize_apply_detailed {
    /**
     * test_synthesize_apply_id
     */
    private Integer apply_id;
    /**
     * 项目id
     */
    private Integer test_synthesize_id;
    /**
     * 状态
     */
    private Integer status;

    @TableField(exist = false)
    private TestSynthesize patientHandle;
}

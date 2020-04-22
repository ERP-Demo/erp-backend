package cn.shiying.test_projects.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TestProjects implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 化验项目的id
     */
    @TableId(value = "test_projects_id", type = IdType.ID_WORKER_STR)
    private String testProjectsId;

    /**
     * 化验项目的简称
     */
    private String testAbbreviation;

    /**
     * 化验项目的全称
     */
    private String testName;

    /**
     * 参考下限
     */
    private Integer floor;

    /**
     *  参考上限
     */
    private Integer ceiling;

    /**
     * 数值单位
     */
    private String unit;


}

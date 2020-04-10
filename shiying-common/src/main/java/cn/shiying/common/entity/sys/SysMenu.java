package cn.shiying.common.entity.sys;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer menuId;
    private Integer parentId;
    private String name;
    private String url;
    @TableField(strategy= FieldStrategy.IGNORED)
    private String perms;
    private Integer type;
    private String icon;
    private Integer orderNum;

    /**
     * 父菜单名称
     */
    @TableField(exist=false)
    private String parentName;
    /**
     * z-tree属性
     */
    @TableField(exist=false)
    private Boolean open;

    @TableField(exist=false)
    private List<?> list;



}

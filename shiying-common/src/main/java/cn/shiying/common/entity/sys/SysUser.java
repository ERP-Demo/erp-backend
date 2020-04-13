package cn.shiying.common.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private Integer createUserId;
    private Date createTime;
    private Integer status;

    @TableField(exist=false)
    private List<Integer> roleIdList;

    @TableField(exist=false)
    private List<String> perms;
}

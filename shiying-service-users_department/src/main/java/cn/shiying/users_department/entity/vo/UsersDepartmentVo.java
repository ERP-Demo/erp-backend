package cn.shiying.users_department.entity.vo;

import cn.shiying.common.entity.department.Department;
import cn.shiying.common.entity.sys.SysUser;
import cn.shiying.users_department.entity.UsersDepartment;
import lombok.Data;

@Data
public class UsersDepartmentVo extends UsersDepartment {

    private Department department;

    private SysUser user;
}

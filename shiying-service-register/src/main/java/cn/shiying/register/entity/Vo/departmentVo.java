package cn.shiying.register.entity.Vo;

import cn.shiying.common.entity.department.Department;
import cn.shiying.register.entity.Register;
import lombok.Data;

@Data
public class departmentVo extends Register {

    private Department department;
    private Register register;
}

package cn.shiying.register.entity.form;

import lombok.Data;

@Data
public class RegisterForm {
    private Integer uid;
    private Integer departmentId;

    public RegisterForm() {
    }

    public RegisterForm(Integer uid, Integer departmentId) {
        this.uid = uid;
        this.departmentId = departmentId;
    }
}

package cn.shiying.common.entity.token;

import lombok.Data;

import java.util.List;

@Data
public class JwtUser {
    private int uid;
    private String username;
    private List<Integer> departmentId;

}

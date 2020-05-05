package cn.shiying.drugs_purchase.entity.form;

import lombok.Data;

import java.util.List;

@Data
public class CheckForm {
    private List<String> ids;
    private String reason;
}

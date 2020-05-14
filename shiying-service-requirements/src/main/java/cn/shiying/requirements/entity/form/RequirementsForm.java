package cn.shiying.requirements.entity.form;

import cn.shiying.requirements.entity.Requirements;
import lombok.Data;

import java.util.List;

@Data
public class RequirementsForm {
    private List<Requirements> list;
    private Integer patientId;
    private String registerId;
}

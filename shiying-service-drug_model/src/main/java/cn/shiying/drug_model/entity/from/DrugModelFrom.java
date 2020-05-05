package cn.shiying.drug_model.entity.from;

import cn.shiying.drug_model.entity.DrugModel;
import lombok.Data;

import java.util.List;

@Data
public class DrugModelFrom {
    private DrugModel drugModel;
    private Integer drugModelId;
    private List<Integer> ids;
}

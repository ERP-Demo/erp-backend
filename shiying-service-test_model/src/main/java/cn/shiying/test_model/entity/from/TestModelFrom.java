package cn.shiying.test_model.entity.from;

import cn.shiying.test_model.entity.TestModel;
import lombok.Data;

import java.util.List;
@Data
public class TestModelFrom {
    private TestModel testModel;
    private Integer testModelId;
    private List<Integer> ids;
}

package cn.shiying.requirements.entity.form;

import cn.shiying.requirements.entity.LaboratoryList;
import cn.shiying.requirements.entity.Requirements;
import lombok.Data;

import java.util.List;

@Data
public class TestSheetForm {
    private List<LaboratoryList> dataList;
}

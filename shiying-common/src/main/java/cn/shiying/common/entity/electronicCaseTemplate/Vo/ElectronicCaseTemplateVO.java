package cn.shiying.common.entity.electronicCaseTemplate.Vo;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.common.entity.electronicCaseTemplate.ElectronicCaseTemplate;
import lombok.Data;

import java.util.List;

@Data
public class ElectronicCaseTemplateVO extends ElectronicCaseTemplate {
    List<Icd> icds;
}

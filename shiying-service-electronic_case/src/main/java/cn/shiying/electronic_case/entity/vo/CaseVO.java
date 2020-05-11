package cn.shiying.electronic_case.entity.vo;

import cn.shiying.common.entity.Icd.Icd;
import cn.shiying.electronic_case.entity.ElectronicCase;
import cn.shiying.electronic_case.entity.ElectronicCaseDetailed;
import lombok.Data;

import java.util.List;

@Data
public class CaseVO {
    private ElectronicCase electronicCase;
    private List<Icd> icds;
}

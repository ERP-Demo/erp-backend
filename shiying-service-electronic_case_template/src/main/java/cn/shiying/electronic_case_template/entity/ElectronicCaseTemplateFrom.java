package cn.shiying.electronic_case_template.entity;

import lombok.Data;

import java.util.List;

@Data
public class ElectronicCaseTemplateFrom {
    private ElectronicCaseTemplate electronicCaseTemplate;
    private List<Integer> ids;
}
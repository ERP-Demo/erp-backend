package cn.shiying.test_synthesize.entity.form;

import lombok.Data;

import java.util.List;

@Data
public class SynthesizeAndProjects {

    private String synthesizeName;

    private double synthesizPrice;

    private List<correlation> detailed;

}

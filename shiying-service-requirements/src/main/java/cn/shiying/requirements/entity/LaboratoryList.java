package cn.shiying.requirements.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LaboratoryList implements Serializable {

    private Integer id;

    private Integer testProjectsId;

    private Double result;

}

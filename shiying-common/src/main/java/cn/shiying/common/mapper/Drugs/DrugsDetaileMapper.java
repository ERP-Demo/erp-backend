package cn.shiying.common.mapper.Drugs;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import cn.shiying.common.entity.Drugs.vo.DrugsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-16
 */
@Mapper
public interface DrugsDetaileMapper extends BaseMapper<DrugsDetailed> {
    List<DrugsDetailed> selectByddId(Integer drugsId);

    List<DrugsVo> selectAll();
}

package cn.shiying.common.mapper.Drugs;

import cn.shiying.common.entity.Drugs.DrugsDetailed;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface DrugsDetailedMapper extends BaseMapper<DrugsDetailed> {
    List<DrugsDetailed> like(@Param("name") String name);
}

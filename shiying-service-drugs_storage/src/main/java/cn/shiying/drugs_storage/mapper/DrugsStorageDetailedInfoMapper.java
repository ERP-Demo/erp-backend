package cn.shiying.drugs_storage.mapper;

import cn.shiying.drugs_storage.entity.DrugsStorageDetailedInfo;
import cn.shiying.drugs_storage.entity.vo.DrugsDetailedVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tyb
 * @since 2020-04-18
 */
@Mapper
public interface DrugsStorageDetailedInfoMapper extends BaseMapper<DrugsStorageDetailedInfo> {

    List<DrugsDetailedVO> listDrugsDetailedVO();
}

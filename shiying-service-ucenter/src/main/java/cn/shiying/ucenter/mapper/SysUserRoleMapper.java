package cn.shiying.ucenter.mapper;

import cn.shiying.common.entity.sys.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /**
     * 查询roleId
     * @param userId
     * @return
     */
    List<Integer> queryRoleIdList(Integer userId);
}

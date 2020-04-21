package cn.shiying.ucenter.service.impl;

import cn.shiying.common.constants.SysConstants;
import cn.shiying.common.entity.sys.SysRole;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.common.utils.Query;
import cn.shiying.ucenter.mapper.SysRoleMapper;
import cn.shiying.ucenter.service.SysRoleMenuService;
import cn.shiying.ucenter.service.SysRoleService;
import cn.shiying.ucenter.service.SysUserRoleService;
import cn.shiying.ucenter.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * SysRoleServiceImpl
 *
 * @author bobbi
 * @date 2018/10/25 15:36
 * @email 571002217@qq.com
 * @description
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");
        Integer createUserId = (Integer)params.get("createUserId");
        IPage<SysRole> page=baseMapper.selectPage(new Query<SysRole>(params).getPage(),
                new QueryWrapper<SysRole>().lambda()
                .like(StringUtils.isNotBlank(roleName), SysRole::getRoleName,roleName)
                .eq(createUserId!=null, SysRole::getCreateUserId,createUserId)
        );
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Integer[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatchByRoleId(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatchByRoleId(roleIds);
    }

    /**
     * 查询roleId
     *
     * @param createUserId
     * @return
     */
    @Override
    public List<Integer> queryRoleIdList(Integer createUserId) {
        return baseMapper.queryRoleIdList(createUserId) ;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysRole role) {
        role.setCreateTime(new Date());
        baseMapper.insert(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(SysRole role){
        baseMapper.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
        return true;
    }


    /**
     * 检查权限是否越权
     */
    private void checkPrems(SysRole role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(SysConstants.SUPER_ADMIN.equals(role.getCreateUserId())){
            return ;
        }

        //查询用户所拥有的菜单列表
        List<Integer> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            ExceptionCast.cast(ErrorEnum.NEED_MORE_AUTH);
        }
    }
}

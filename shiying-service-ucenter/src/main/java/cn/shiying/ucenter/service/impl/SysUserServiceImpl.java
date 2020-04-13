package cn.shiying.ucenter.service.impl;

import cn.shiying.common.entity.sys.SysMenu;
import cn.shiying.common.entity.sys.SysUser;
import cn.shiying.common.utils.PageUtils;
import cn.shiying.ucenter.mapper.SysMenuMapper;
import cn.shiying.ucenter.mapper.SysUserMapper;
import cn.shiying.ucenter.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bobbi
 * @since 2018-10-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

//    @Autowired
//    private SysUserRoleService sysUserRoleService;
//
//    @Autowired
//    @Lazy
//    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    private final static Integer SUPER_ADMIN=1;


    /**
     * 查询用户菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Integer> queryAllMenuId(Integer userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    @Override
    public SysUser selectByUsername(String username) {
        SysUser user=baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username",username));
        if (SUPER_ADMIN==user.getUserId()){
            List<SysMenu> menuList=sysMenuMapper.selectList(null);
            List<String> permsList=new ArrayList<>(menuList.size());
            menuList.forEach(menu ->  permsList.add(menu.getPerms()));
            user.setPerms(permsList);
        }else {
            user.setPerms(baseMapper.queryAllPerms(user.getUserId()));
        }
        return user;
    }

    /**
     * 分页查询用户信息
     *
     * @param params
     * @return
     */
//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        String username = (String)params.get("username");
//        Integer createUserId = (Integer)params.get("createUserId");
//        IPage<SysUser> page = baseMapper.selectPage(
//                new Query<SysUser>(params).getPage(),
//                new QueryWrapper<SysUser>().lambda()
//                        .like(StringUtils.isNotBlank(username),SysUser::getUsername, username)
//                        .eq(createUserId != null,SysUser::getCreateUserId, createUserId));
//        return new PageUtils(page);
//    }

    /**
     * 更新密码
     *
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
//    @Override
//    public boolean updatePassword(Integer userId, String password, String newPassword) {
//        SysUser sysUser=new SysUser();
//        sysUser.setPassword(newPassword);
//        return this.update(sysUser, new UpdateWrapper<SysUser>().lambda()
//                .eq(SysUser::getUserId,userId).eq(SysUser::getPassword,password));
//    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean save(SysUser user) {
//        user.setCreateTime(new Date());
//        //sha256加密
//        String salt = RandomStringUtils.randomAlphanumeric(20);
//        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
//        user.setSalt(salt);
//        this.baseMapper.insert(user);
//
//        //检查角色是否越权
//        checkRole(user);
//
//        //保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
//        return true;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean updateById(SysUser user) {
//        if(StringUtils.isBlank(user.getPassword())){
//            user.setPassword(null);
//        }else{
//            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
//        }
//        this.baseMapper.updateById(user);
//
//        //检查角色是否越权
//        checkRole(user);
//
//        //保存用户与角色关系
//        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
//        return true;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteBatch(Integer[] userIds) {
//        this.removeByIds(Arrays.asList(userIds));
//        //删除用户与角色关联
//        sysUserRoleService.deleteBatchByUserId(userIds);
//
//    }
//
//    /**
//     * 检查角色是否越权
//     */
//    private void checkRole(SysUser user){
//        if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
//            return;
//        }
//        //如果不是超级管理员，则需要判断用户的角色是否自己创建
//        if(SysConstants.SUPER_ADMIN.equals(user.getCreateUserId())){
//            return ;
//        }
//
//        //查询用户创建的角色列表
//        List<Integer> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
//
//        //判断是否越权
//        if(!roleIdList.containsAll(user.getRoleIdList())){
//            throw new MyException("新增用户所选角色，不是本人创建");
//        }
//    }

}

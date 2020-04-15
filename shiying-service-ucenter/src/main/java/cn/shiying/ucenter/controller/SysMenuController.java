package cn.shiying.ucenter.controller;


import cn.shiying.common.entity.sys.SysMenu;
import cn.shiying.ucenter.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */

@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/menu/{uid}")
    public List<SysMenu> menu(Integer uid){
        List<SysMenu> menuList=sysMenuService.listUserMenu(uid);
        return menuList;
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.list(null);
        menuList.forEach(sysMenu -> {
            SysMenu parentMenu = sysMenuService.getById(sysMenu.getParentId());
            if(parentMenu != null){
                sysMenu.setParentName(parentMenu.getName());
            }
        });
        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
//    @GetMapping("/select")
//    @RequiresPermissions("sys:menu:select")
//    public Result select(){
//        //查询列表数据
//        List<SysMenu> menuList = sysMenuService.queryNotButtonList();
//
//        //添加顶级菜单
//        SysMenu root = new SysMenu();
//        root.setMenuId(0);
//        root.setName("一级菜单");
//        root.setParentId(-1);
//        root.setOpen(true);
//        menuList.add(root);
//
//        return Result.ok().put("menuList", menuList);
//    }

    /**
     * 获取单个菜单信息
     * @param menuId
     * @return
     */
//    @GetMapping("/info/{menuId}")
//    @RequiresPermissions("sys:menu:info")
//    public Result update(@PathVariable Integer menuId){
//        SysMenu menu = sysMenuService.getById(menuId);
//        return Result.ok().put("menu",menu);
//    }

    /**
     * 保存
     */
//    @PostMapping("/save")
//    @RequiresPermissions("sys:menu:save")
//    public Result save(@RequestBody SysMenu menu){
//        //数据校验
//        verifyForm(menu);
//
//        sysMenuService.save(menu);
//
//        return Result.ok();
//    }

    /**
     * 更新
     * @param menu
     * @return
     */
//    @PutMapping("/update")
//    @RequiresPermissions("sys:menu:update")
//    public Result update(@RequestBody SysMenu menu){
//        //数据校验
//        verifyForm(menu);
//
//        sysMenuService.updateById(menu);
//
//        return Result.ok();
//    }

    /**
     * 删除
     * @param menuId
     * @return
     */
//    @DeleteMapping("/delete/{menuId}")
//    @RequiresPermissions("sys:menu:delete")
//    public Result delete(@PathVariable Integer menuId){
//        if(menuId <= 29){
//            return Result.error("系统菜单，不能删除");
//        }
//
//        //判断是否有子菜单或按钮
//        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
//        if(menuList.size() > 0){
//            return Result.error("请先删除子菜单或按钮");
//        }
//        sysMenuService.delete(menuId);
//        return Result.ok();
//    }
    /**
     * 验证参数是否正确
     */
//    private void verifyForm(SysMenu menu) {
//        if (StringUtils.isBlank(menu.getName())) {
//            throw new MyException("菜单名称不能为空");
//        }
//
//        if (menu.getParentId() == null) {
//            throw new MyException("上级菜单不能为空");
//        }
//
//        //菜单
//        if (menu.getType() == MenuTypeEnum.MENU.getValue()) {
//            if (StringUtils.isBlank(menu.getUrl())) {
//                throw new MyException("菜单URL不能为空");
//            }
//        }
//
//        //上级菜单类型
//        int parentType = MenuTypeEnum.CATALOG.getValue();
//        if (menu.getParentId() != 0) {
//            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
//            parentType = parentMenu.getType();
//        }
//
//        //目录、菜单
//        if (menu.getType() == MenuTypeEnum.CATALOG.getValue() ||
//                menu.getType() == MenuTypeEnum.MENU.getValue()) {
//            if (parentType != MenuTypeEnum.CATALOG.getValue()) {
//                throw new MyException("上级菜单只能为目录类型");
//            }
//        }
//
//        //按钮
//        if (menu.getType() == MenuTypeEnum.BUTTON.getValue()) {
//            if (parentType != MenuTypeEnum.MENU.getValue()) {
//                throw new MyException("上级菜单只能为菜单类型");
//            }
//        }
//    }

}

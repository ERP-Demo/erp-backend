package cn.shiying.ucenter.controller;


import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.sys.SysMenu;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.enums.MenuTypeEnum;
import cn.shiying.common.exception.ExceptionCast;
import cn.shiying.ucenter.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author bobbi
 * @since 2018-10-19
 */

@RequestMapping("/menu")
@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/menu/{uid}")
    public List<SysMenu> menu(@RequestParam("uid") Integer uid){
        List<SysMenu> menuList=sysMenuService.listUserMenu(uid);
        return menuList;
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public Result list(){
        List<SysMenu> menuList = sysMenuService.list(null);
        menuList.forEach(sysMenu -> {
            SysMenu parentMenu = sysMenuService.getById(sysMenu.getParentId());
            if(parentMenu != null){
                sysMenu.setParentName(parentMenu.getName());
            }
        });
        return Result.ok().put("menuList",menuList);
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @PreAuthorize("hasAuthority('sys:menu:select')")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0);
        root.setName("一级菜单");
        root.setParentId(-1);
        root.setOpen(true);
        menuList.add(root);

        return Result.ok().put("menuList", menuList);
    }

    /**
     * 获取单个菜单信息
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:info')")
    public Result update(@PathVariable Integer menuId){
        SysMenu menu = sysMenuService.getById(menuId);
        return Result.ok().put("menu",menu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    public Result save(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return Result.ok();
    }

    /**
     * 更新
     * @param menu
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public Result update(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return Result.ok();
    }

    /**
     * 删除
     * @param menuId
     * @return
     */
    @DeleteMapping("/delete/{menuId}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public Result delete(@PathVariable Integer menuId){
        if(menuId <= 29){
            return Result.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return Result.error("请先删除子菜单或按钮");
        }
        sysMenuService.delete(menuId);
        return Result.ok();
    }
    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            ExceptionCast.cast(ErrorEnum.MENU_NAME_IS_NULL);
        }

        if (menu.getParentId() == null) {
            ExceptionCast.cast(ErrorEnum.MENU_PARENT_IS_NULL);
        }

        //菜单
        if (menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                ExceptionCast.cast(ErrorEnum.MENU_URL_IS_NULL);
            }
        }

        //上级菜单类型
        int parentType = MenuTypeEnum.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuTypeEnum.CATALOG.getValue() ||
                menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (parentType != MenuTypeEnum.CATALOG.getValue()) {
                ExceptionCast.cast(ErrorEnum.MENU_PARENT_NO_CATALOG);
            }
        }

        //按钮
        if (menu.getType() == MenuTypeEnum.BUTTON.getValue()) {
            if (parentType != MenuTypeEnum.MENU.getValue()) {
                ExceptionCast.cast(ErrorEnum.MENU_PARENT_NO_MENU);
            }
        }
    }

}

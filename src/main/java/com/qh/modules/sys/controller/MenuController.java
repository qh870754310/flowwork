package com.qh.modules.sys.controller;

import com.qh.modules.common.annotation.SysLog;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.utils.ShiroUtils;
import com.qh.modules.common.utils.StringUtils;
import com.qh.modules.sys.entity.MenuEntity;
import com.qh.modules.sys.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 菜单表
 *
 * Created by Administrator on 2018/5/10.
 */
@RestController
@RequestMapping("sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:menu:info")
    @SysLog("查看菜单")
    public Result info(@PathVariable("id") String id) {
        MenuEntity menu = menuService.queryObject(id);
        return Result.ok().put("menu", menu);
    }

    /**
     * 保存
     *
     * @param menu
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:update")
    @SysLog("保存菜单")
    public Result save(@RequestBody MenuEntity menu) {
        String id = menuService.save(menu);
        MenuEntity menuEntity = menuService.queryObject(id);
        return Result.ok().put("menu", menuEntity);
    }

    /**
     * 修改
     * @param menu
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    @SysLog("修改菜单")
    public Result update(@RequestBody MenuEntity menu) {
        menuService.update(menu);
        return Result.ok().put("menu", menu);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@RequestBody String ids) {
        menuService.deleteBatch(StringUtils.getArrayByArray(ids.split(",")));
        return Result.ok();
    }

    /**
     * 查询当前用户授权菜单
     *
     * @return
     */
    @RequestMapping(value = "/userMenu")
    public Result userMenu() {
        List<MenuEntity> menuEntities = menuService.queryListUser(ShiroUtils.getUserId());
        return Result.ok().put("menuList", menuEntities);
    }

    /**
     * 角色授权
     *
     * @return
     */
    @RequestMapping(value = "/perms")
    public Result perms() {
        //查询列表数据
        List<MenuEntity> menuList = null;
        //只有超级管理员才可以查看所有管理员列表
        if (getUserId().equals(Constant.SUPERR_USER)) {
            menuList = menuService.queryList(new HashMap<String, Object>());
        } else {
            menuList = menuService.queryByUserId(getUserId());
        }
        return Result.ok().put("menuList", menuList);
    }


    /**
     * 查询除按钮外的其他资源菜单
     * @return
     */
    @RequestMapping(value = "/selectMenu")
    @RequiresPermissions("sys:menu:list")
    public Result selectMenu() {
        List<MenuEntity> menuEntities = menuService.queryNotButtonList();
        return Result.ok().put("menuEntities", menuEntities);
    }

}

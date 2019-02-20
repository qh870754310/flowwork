package com.qh.modules.sys.controller;

import com.qh.modules.activiti.service.ActModelService;
import com.qh.modules.common.annotation.DataAuth;
import com.qh.modules.common.annotation.SysLog;
import com.qh.modules.common.common.Constant;
import com.qh.modules.common.controller.BaseController;
import com.qh.modules.common.page.Page;
import com.qh.modules.common.utils.PageUtils;
import com.qh.modules.common.utils.Query;
import com.qh.modules.common.utils.Result;
import com.qh.modules.common.utils.ShiroUtils;
import com.qh.modules.sys.entity.NoticeEntity;
import com.qh.modules.sys.entity.UserEntity;
import com.qh.modules.sys.service.NoticeService;
import com.qh.modules.sys.service.UserRoleService;
import com.qh.modules.sys.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户表
 *
 * Created by Administrator on 2018/5/11.
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private ActModelService actModelService;

    @RequestMapping("/list")
    @RequiresPermissions("sys:user:list")
    @SysLog("查看系统用户列表")
    @DataAuth(tableAlias = "s")
    public Result list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<UserEntity> userList = userService.queryList(query);
        int total = userService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
        return Result.ok().put("page", pageUtil);
    }

    /**
     * 信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:user:info")
    @SysLog("查看系统用户信息")
    public Result info(@PathVariable("id") String id) {
        UserEntity user = userService.queryObject(id);
        if (user != null) {
            user.setPassWord("");
            user.setRoleIdList(userRoleService.queryRoleIdList(user.getId()));
        }
        return Result.ok().put("user", user);
    }

    /**
     * 主页用户信息
     *
     * @return
     */
    @RequestMapping("/info")
    public Result info() {
        UserEntity user = userService.queryObject(ShiroUtils.getUserId());
        //待办条数
        int myUpcomingCount = actModelService.myUpcomingCount();
        //我的通知条数
       int myNoticeCount  = noticeService.MyNoticeCount();
       return Result.ok().put("user", user).put("myUpcomingCount", myUpcomingCount).put("myNoticeCount", myNoticeCount);
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:user:update")
    @SysLog("新增系统用户")
    @ApiOperation(value = "新增系统用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    public Result save(@RequestBody UserEntity user) {
        userService.save(user);
        return Result.ok();
    }

    /**
     * 修改
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:user:update")
    @SysLog("修改系统用户")
    @ApiOperation(value = "修改系统用户", notes = "根据User对象更新用户")
    @ApiImplicitParam(name = "user", value = "user", required = true, dataType = "User")
    public Result update(@RequestBody UserEntity user) {
        user.setPassWord(null);
        userService.update(user);
        return Result.ok();
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @SysLog("用户修改密码")
    @ApiOperation(value = "修改密码", notes = "根据id修改密码")
    @ApiImplicitParam(name = "user", value = "user", required = true, dataType = "User")
    public Result updatePassword(UserEntity user) {
        int i = userService.updatePassword(user);
        if (i < 1) {
            return Result.error("更改密码失败");
        }
        return Result.ok("更改密码成功");
    }

    /**
     * 禁用
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    @SysLog("禁用系统用户")
    @ApiOperation(value = "禁用系统用户", notes = "根据url的ids来指定删除对象")
    @ApiImplicitParam(name = "ids", value = "用户id数组", required = true, dataType = "String")
    public Result delete(@RequestBody String[] ids) {
        userService.updateBatchStatus(ids, Constant.ABLE_STATUS.NO.getValue());
        return Result.ok();
    }

    /**
     * 启用
     *
     * @param ids
     * @return
     */
    @RequestMapping("/enabled")
    @RequiresPermissions("sys:user:enabled")
    @SysLog("启用系统用户")
    @ApiOperation(value = "启用系统用户", notes = "根据id启用系统用户")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    public Result enabled(@RequestBody String[] ids) {
        userService.updateBatchStatus(ids, Constant.ABLE_STATUS.YES.getValue());
        return Result.ok();
    }

    /**
     * 重置密码
     * @param ids
     * @return
     */
    @RequestMapping("/reset")
    @RequiresPermissions("sys:user:reset")
    @SysLog("重置密码")
    @ApiOperation(value = "重置密码", notes = "根据id重置密码")
    @ApiImplicitParam(name = "ids", value = "ids", required = true, dataType = "String")
    public Result reset(@RequestBody String[] ids) {
        userService.resetPassWord(ids);
        return Result.ok("重置密码成功,密码为: " + Constant.DEF_PASSWORD);
    }
}

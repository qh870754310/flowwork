package com.qh.modules.app.controller;

import com.qh.modules.app.annotation.CurrentUser;
import com.qh.modules.app.annotation.LoginRequired;
import com.qh.modules.app.entity.ApiUserEntity;
import com.qh.modules.app.service.ApiUserService;
import com.qh.modules.common.utils.Result;
import com.qh.modules.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/28.
 */
@Controller
@RequestMapping("/app/user")
public class ApiUserController {

    @Autowired
    private ApiUserService apiUserService;

    /**
     * 用户信息
     * @param apiUserEntity
     * @return
     */
    @LoginRequired
    @ResponseBody
    public Result info(@CurrentUser ApiUserEntity apiUserEntity) {
        ApiUserEntity user = apiUserService.userInfo(apiUserEntity.getId());
        return Result.ok().put("user", user);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public Result update(UserEntity user) {
        apiUserService.update(user);
        return Result.ok();
    }

    /**
     * 修改当前用户密码
     *
     * @param newUser
     * @param apiUserEntity
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public Result updatePassword(UserEntity newUser, @CurrentUser ApiUserEntity apiUserEntity) {
        int i = apiUserService.updatePassword(newUser, apiUserEntity);
        if (i < 1) {
            return Result.error("更改密码失败");
        }
        return Result.ok("更改密码成功");
    }
}

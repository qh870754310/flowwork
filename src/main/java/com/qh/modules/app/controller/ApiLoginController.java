package com.qh.modules.app.controller;

import com.qh.modules.app.service.ApiUserService;
import com.qh.modules.app.utils.JwtUtils;
import com.qh.modules.common.utils.Result;
import com.qh.modules.sys.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/28.
 */
@Controller
@RequestMapping("/app")
public class ApiLoginController {

    @Autowired
    private ApiUserService apiUserService;

    @Autowired
    private JwtUtils jwtUtils;


    /**
     * 登录
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody UserEntity userEntity) {
        //用户登录
        String userId = apiUserService.login(userEntity);

        //生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return Result.ok(map);

    }

}

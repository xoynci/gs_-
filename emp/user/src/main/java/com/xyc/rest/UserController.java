package com.xyc.rest;

import com.alibaba.fastjson.JSONObject;
import com.xyc.pojo.User;
import com.xyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/insertUser",method = {RequestMethod.GET,RequestMethod.POST})
    public String insertUser(@RequestBody JSONObject jsonRequest){
        System.out.println(JSONObject.toJSONString(jsonRequest));
        User user=JSONObject.toJavaObject(jsonRequest,User.class);
        userService.insert(user);
//        System.out.println("User:"+ JSONObject.toJSONString(user));
        return "success";
    }
}

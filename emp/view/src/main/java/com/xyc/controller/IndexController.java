package com.xyc.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xyc.pojo.Dept;
import com.xyc.pojo.User;
import com.xyc.service.IndexServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    IndexServer indexServer;

    @HystrixCommand(fallbackMethod = "adduserFallback")
    @RequestMapping(value = "/adduser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String adduser(User user){
        System.out.println(JSONObject.toJSONString(user));
        return indexServer.insertUser(user);
    }

    @HystrixCommand(fallbackMethod = "useraddFallback")
    @RequestMapping(value = "/useradd",method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView useradd(){
        ModelAndView modelAndView= new ModelAndView("/useradd");
        List<Dept> depts = indexServer.getDepts();
        modelAndView.addObject("depts",depts);
        return modelAndView;
    }

    public ModelAndView useraddFallback(){
        ModelAndView modelAndView= new ModelAndView("/fallback");
        return modelAndView;
    }
    public String adduserFallback(User user){
        return "服务暂时不可用！";
    }

    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView= new ModelAndView("/index");
        return modelAndView;
    }

    @RequestMapping(value = "/home",method = {RequestMethod.GET})
    @ResponseBody
    public ModelAndView home(){
        ModelAndView modelAndView= new ModelAndView("/index");
        return modelAndView;
    }

    @RequestMapping(value = "/getdepts",method = {RequestMethod.GET})
    @ResponseBody
    public List<Dept> getdeps(){
        return indexServer.getDepts();
    }

    @RequestMapping(value = "/test",method = {RequestMethod.GET})
    @ResponseBody
    public String test(){
        return "this test url!";
    }


}

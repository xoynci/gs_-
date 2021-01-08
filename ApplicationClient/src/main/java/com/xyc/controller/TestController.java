package com.xyc.controller;

import com.xyc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    TestService testserver;

    @RequestMapping("/home")
    @ResponseBody
    public String home(){

        return testserver.getServerUil();
    }

    @RequestMapping("/toHome")
    @ResponseBody
    public String toHome(){
        return testserver.toHome();
    }
}

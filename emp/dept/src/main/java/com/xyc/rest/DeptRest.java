package com.xyc.rest;

import com.xyc.pojo.Dept;
import com.xyc.service.DeptService;
import com.xyc.service.impl.DeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptRest {

    @Autowired
    DeptServiceImpl deptService;

    @RequestMapping(value = "/getdeptall",method = {RequestMethod.POST,RequestMethod.GET})
    public List<Dept> getAll(){
        List<Dept> depts;
        return depts=deptService.getAll();
    }

}

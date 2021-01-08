package com.xyc.service.impl;

import com.xyc.dao.DeptDao;
import com.xyc.pojo.Dept;
import com.xyc.service.DeptService;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    SQLManager sqlManager;

    @Autowired
    DeptDao deptDao;

    @Override
    public List<Dept> getAll() {
        List<Dept> depts=deptDao.all();
        return depts;
    }
}

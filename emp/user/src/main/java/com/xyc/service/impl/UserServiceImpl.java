package com.xyc.service.impl;

import com.xyc.dao.UserDao;
import com.xyc.pojo.User;
import com.xyc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao dao;

    @Override
    public void insert(User user) {
       dao.insert(user);
    }
}

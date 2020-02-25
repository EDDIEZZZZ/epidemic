package com.demo.epidemic.service.impl;

import com.demo.epidemic.beans.UserInfo;
import com.demo.epidemic.mapper.UserMapper;
import com.demo.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author eddie
 * @Date 2020-02-25 14:34
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}

package com.demo.epidemic.service;

import com.demo.epidemic.beans.UserInfo;

/**
 * @Author eddie
 * @Date 2020-02-25 14:26
 * @Version 1.0
 */
public interface UserService {
    /**
     *  根据用户的账号获取信息
     * @param account 账号
     * @return 用户信息
     */
    UserInfo findByAccount(String account);
}

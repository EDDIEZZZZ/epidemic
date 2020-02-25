package com.demo.epidemic.controller;

import com.demo.epidemic.beans.UserInfo;
import com.demo.epidemic.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author eddie
 * @Date 2020-02-25 13:06
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    public static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * @param userInfo 用户前端传入信息
     * @param model    登录信息显示
     * @return 用户登录
     */
    @RequestMapping("login")
    public String login(UserInfo userInfo, Model model) {
        logger.debug("login()方法被执行----------" + userInfo.getAccount() + userInfo.getPassword());
        //通过业务逻辑层的bean获取该账号对应的用户信息
        UserInfo user = userService.findByAccount(userInfo.getAccount());
        if (user == null) {
            //账号不正确
            model.addAttribute("msg", "账号不正确");
            return "login";
        }
        if (user.getPassword().equals(userInfo.getPassword())) {
            //登录成功
            return "main";
        }
        //密码不正确
        model.addAttribute("msg", "密码不正确");
        return "login";
    }

}

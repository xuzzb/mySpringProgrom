package com.dcits.controller.login;


import com.dcits.dao.UserLoginMapper;
import com.dcits.entity.UserLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserLoginMapper userLoginMapper;
    @RequestMapping("/login")
    public String login(){
        logger.info("pring the login page");
        return "page/login";
    }
    /**
     * 开始验证是否登录成功
     * @return
     */
    @RequestMapping(value = "/vertify")
    public String vertify(String userName,String password){
        logger.info("the user name is" + userName);
        logger.info("the user password is" + password);
        logger.info("now you start select userinfo from mysql");
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(userName);
        userLogin.setPassword(password);
        List<UserLogin> userLogins = userLoginMapper.loginByUser(userLogin);
        userLogins.forEach(result->{
            logger.info("this is the result to mysql select"+result);
        });
        if(userLogins != null && userLogins.size() > 0){
            return "page/index";
        }else {
            return "page/failed";
        }
    }
}

package com.next.xujing.film.service.user;

import com.next.xujing.film.controller.user.vo.EnrollUserVO;
import com.next.xujing.film.controller.user.vo.UserInfoVO;
import com.next.xujing.film.service.common.exception.CommonServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private UserServiceAPI userServiceAPI;

    @Test
    public void userEnroll() throws CommonServiceException {
        EnrollUserVO enrollUserVO = new EnrollUserVO();
        enrollUserVO.setUsername("1");
        enrollUserVO.setPassword("1");
        enrollUserVO.setAddress("1");
        enrollUserVO.setEmail("1");
        enrollUserVO.setPhone("1");
        userServiceAPI.userEnroll(enrollUserVO);

    }

    @Test
    public void checkUserName() {
    }

    @Test
    public void userAuth() {
    }

    @Test
    //测试用户信息是否存在
    public void describeUserInfo() throws CommonServiceException {
        String userId = "2";
        UserInfoVO userInfoVO = userServiceAPI.describeUserInfo(userId);
        System.out.println(userInfoVO);

    }

    @Test
    public void updateUserInfo() {
    }
}
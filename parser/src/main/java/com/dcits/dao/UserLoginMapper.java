package com.dcits.dao;

import com.dcits.entity.UserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-20
 */
public interface UserLoginMapper extends BaseMapper<UserLogin> {
    List<UserLogin> loginByUser(UserLogin userLogin);
}

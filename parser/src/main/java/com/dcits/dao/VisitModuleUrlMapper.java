package com.dcits.dao;

import com.dcits.entity.VisitModuleUrl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-23
 */
public interface VisitModuleUrlMapper extends BaseMapper<VisitModuleUrl> {
    /**
     * 首页网站地址获取，返回为一个VisisModuleUrl对象，输入为一个Id
     * 返回一个模块首页地址
     * @param id
     * @return
     */
    VisitModuleUrl selectPhotoImageById(Integer id);
}

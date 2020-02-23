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
    VisitModuleUrl selectPhotoImageById(Integer id);
}

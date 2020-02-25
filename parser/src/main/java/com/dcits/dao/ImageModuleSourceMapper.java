package com.dcits.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dcits.entity.ImageModule;
import com.dcits.entity.ImageModuleSource;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-21
 */
public interface ImageModuleSourceMapper extends BaseMapper<ImageModuleSource> {

    void insertImageModuleSource(ImageModuleSource imageModuleSource);

    ImageModuleSource getImageModuleSourceById(int id);

    List<ImageModuleSource> getImageModuleSourceList();

    void updateDownLoadStatusByName(String imageSourceUrl);

}

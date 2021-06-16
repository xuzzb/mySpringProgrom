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

    /**
     * 获取image_moduel_source表中的down_load_status = 0 的需要下载的数据集合
     * @return
     */
    List<ImageModuleSource> getImageModuleSourceList(String moduleName);

    void updateDownLoadStatusByName(String imageSourceUrl);

}

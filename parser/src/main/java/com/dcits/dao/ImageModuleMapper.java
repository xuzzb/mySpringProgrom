package com.dcits.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dcits.entity.ImageModule;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-21
 */
public interface ImageModuleMapper extends BaseMapper<ImageModule> {
    void insertImageModule(ImageModule imageModule);

    /**
     * 根据图片模块，获取图片模块未访问的链接地址
     * @param moduleName
     * @return
     */
    List<ImageModule> getImageModulesByImageModule(String moduleName);

    /**
     * 过去image_module中的所有信息
     * @return
     */
    List<ImageModule> getImageModuleByImageModuleIndex(String moduleName);

    /**
     * 根据Id获取信息，查看模块表中，是否已经存在访问信息
     * @param id
     * @return
     */
    ImageModule getImageModuleById(int id);

    /**
     * 根据Id修改image_module中的status='1 and status_info = 'Success'
     * @param id
     */
    void updateImageModuleById(int id);
}

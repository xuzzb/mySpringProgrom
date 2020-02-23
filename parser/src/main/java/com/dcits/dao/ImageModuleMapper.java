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



    List<ImageModule> getImageModulesByImageModule();
}

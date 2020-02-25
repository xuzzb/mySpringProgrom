package com.dcits.dao;

import com.dcits.entity.ImageSourceIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuzzb
 * @since 2020-02-25
 */
public interface ImageSourceIndexMapper extends BaseMapper<ImageSourceIndex> {
    List<ImageSourceIndex> getImageSourceIndexList(int id);


    ImageSourceIndex getImageSourceById(int id);


    void insertImageSourceIndex(ImageSourceIndex imageSourceIndex);
}

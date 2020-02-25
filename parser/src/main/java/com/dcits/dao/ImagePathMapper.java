package com.dcits.dao;

import com.dcits.entity.ImagePath;
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
public interface ImagePathMapper extends BaseMapper<ImagePath> {
    void insertImagePathInfo(ImagePath imagePath);

    List<ImagePath> getImagePath(int index);
}

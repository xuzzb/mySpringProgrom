package com.dcits.dao;

import com.dcits.entity.ImagePathIndex;
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
public interface ImagePathIndexMapper extends BaseMapper<ImagePathIndex> {
    ImagePathIndex getImagePathInfoByIf(int id);
    void insertImagePathIndexInfo(ImagePathIndex imagePathIndex);

    List<ImagePathIndex> getImagePathIndexList(int index);

}

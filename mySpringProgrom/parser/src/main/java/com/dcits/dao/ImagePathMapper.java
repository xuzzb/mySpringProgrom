package com.dcits.dao;

import com.dcits.entity.ImagePath;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据Id获取image_path表中所有这个Id的图片,也通用具有分页功能
     * @param id
     * @return
     */
    List<ImagePath> getImagePathInfoById(@Param("id")int id,@Param("start") int start);

    ImagePath getImagePathById(int id);
}

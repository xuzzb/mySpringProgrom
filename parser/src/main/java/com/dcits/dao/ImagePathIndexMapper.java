package com.dcits.dao;

import com.dcits.entity.ImagePathIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dcits.entity.IndexModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


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

    /**
     * 根据index and moduleName查询需要的首页展示信息
     * @param indexModule
     * @return
     */
    List<ImagePathIndex> getImagePathIndexList(IndexModule indexModule);

}

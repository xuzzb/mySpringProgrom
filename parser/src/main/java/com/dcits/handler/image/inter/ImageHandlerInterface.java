package com.dcits.handler.image.inter;

import com.dcits.entity.ImageModule;

import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/26 11:32
 * @Version 1.0
 * @Since study
 */
public interface ImageHandlerInterface {
    /**
     * 该方法用于从visit表中获取地址，然后去访问图片模块首页
     * @param id
     * @param moduleName
     */
    void getImagesMap(int id,String moduleName);

    /**
     * 根据模块名称，从image_module表中获取未处理的访问信息
     * @param moduleName
     * @return
     */
    List<ImageModule> getImageModule(String moduleName);
    /**
     * 获取图片地址的方法
     * @param imageUrl
     * @param id
     * @param moduleName
     * @param imageName
     */
     void getImageSourceUrl(String imageUrl,int id,String moduleName,String imageName);

    /**
     * 给首页图片表中插入信息，供给展示首页信息
     * @param moduleName
     */
    void insertIntoImagePathIndexImage(String moduleName);
}

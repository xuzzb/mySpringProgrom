package com.dcits.handler.image.inter.download;

import com.dcits.dao.ImageModuleSourceMapper;
import com.dcits.entity.ImageModuleSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/26 19:36
 * @Version 1.0
 * @Since study
 */
public class ImageDownLaodByXun {
    @Resource
    private ImageModuleSourceMapper imageModuleSourceMapper;
    public void getSource() {
        //下载asianPhoto的线程
        String asianPhoto = "asianPhoto";
        List<ImageModuleSource> asianImageModuleSources =
                imageModuleSourceMapper.getImageModuleSourceList(asianPhoto);
        //下载asianPhoto的线程
        String beautifulPhoto = "beautifulPhoto";
        List<ImageModuleSource> beautifulImageModuleSources =
                imageModuleSourceMapper.getImageModuleSourceList(beautifulPhoto);
        //下载asianPhoto的线程
        String europePhoto = "europePhoto";
        List<ImageModuleSource> europeImageModuleSources =
                imageModuleSourceMapper.getImageModuleSourceList(europePhoto);
        //下载asianPhoto的线程
        String legAndWaPhoto = "legAndWaPhoto";
        List<ImageModuleSource> lgeAndWaImageModuleSources =
                imageModuleSourceMapper.getImageModuleSourceList(legAndWaPhoto);
        //下载asianPhoto的线程
        String myselfPhoto = "myselfPhoto";
        List<ImageModuleSource> myselfImageModuleSources =
                imageModuleSourceMapper.getImageModuleSourceList(myselfPhoto);
    }
}

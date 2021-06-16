package com.dcits.handler.image;

import com.dcits.handler.image.inter.impl.ImageHandlerInterfaceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author xuzzhh
 * @Date 2020/2/19 19:46
 * @Version 1.0
 * @Since study
 */
@Component
public class BeautifulPhotoHandler {
    private final Logger logger = LoggerFactory.getLogger(BeautifulPhotoHandler.class);
    @Resource
    private ImageHandlerInterfaceImpl imageHandlerInterfaceImpl;
    /**
     * 此处去访问网站亚洲图片模块，并且将首页的链接地址放在image_module表中
     * @param
     * @return
     */
    //@Scheduled(cron="*/30 * * * * ?")
    public void getImagesMap(){
        int id = 2;
        String moduleName = "beautifulPhoto";
        logger.info("模块Id为"+id+"模块名称为"+moduleName);
        imageHandlerInterfaceImpl.getImagesMap(id,moduleName);
    }

    /**
     * 获取image_module表中的status=0,status_info = 'waid',moduleName='asianPhoto'的记录，
     * 然后依次开始访问，将访问获得的具体图片资源路径，存放到Image_module_source表中
     */
   //  @Scheduled(cron="*/20 * * * * ?")
    public void getImageSourceUrl(){
        String module = "beautifulPhoto";
        //1、查询数据库，获取AsianPhoto的需要处理的数据列表
        imageHandlerInterfaceImpl.getImageSourceUrlToinsert(module);
    }

    @Scheduled(cron="*/120 * * * * ?")
    public void insetIndexInfo(){
        String moduleName = "beautifulPhoto";
        imageHandlerInterfaceImpl.insertIntoImagePathIndexImage(moduleName);
    }


}

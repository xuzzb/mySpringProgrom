package com.dcits.handler.image.inter.impl;

import com.dcits.dao.*;
import com.dcits.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
     * @Author xuzzhh
     * @Date 2020/2/26 11:37
     * @Version 1.0
     * @Since study
     * 用于图片处理公共方法
     */
@Component
public  class ImageHandlerInterfaceImpl{
        private final Logger logger = LoggerFactory.getLogger(ImageHandlerInterfaceImpl.class);
        @Resource
        private ImageModuleMapper imageModuleMapper;

        @Resource
        private ImageModuleSourceMapper imageModuleSourceMapper;

        @Resource
        private VisitModuleUrlMapper visitModuleUrlMapper;

        @Resource
        private ImagePathIndexMapper imagePathIndexMapper;
        @Resource
        private ImagePathMapper imagePathMapper;

    /**
     * 根据Id和moduleName从Visit_image表中获取首页地址
     * @param id
     * @param moduleName
     */
        public void getImagesMap(int id, String moduleName) {
            try {
                //开始访问存放的首页网站地址，通常来说，一个模块只有一个
                VisitModuleUrl visitModuleUrl = visitModuleUrlMapper.selectPhotoImageById(id);
                String url = visitModuleUrl.getModuleUrl();
                logger.info("获取到首页需要访问的地址URL"+url);
                List<ImageModule> imageModuleLists = new ArrayList<ImageModule>();
                Document document = null;
                document = Jsoup.connect(url).get();
                logger.info("创建Document,并且获取到Document的信息"+document);
                logger.info("开始过滤信息.....................");
                Elements elements = document.getElementsByAttributeValueContaining(
                        "href","/ikfl/");
                for (Element element:elements){
                    logger.info("过滤出的链接信息："+element.toString());
                    logger.info("新建一个imageMoudle对象用于存放首页中的每一条记录信息");
                    ImageModule imageModule = new ImageModule();
                    imageModule.setImageName(element.text());
                    imageModule.setModuleName(moduleName);
                    imageModule.setImageModule(id+"");
                    String httpContent = element.toString().substring(9,21);
                    String httpUrl = "http://www.mmff72.com"+element.toString().substring(9,26) ;
                    imageModule.setSourceImage(httpUrl);
                    imageModule.setHttpUrl(url);
                    String imageId = httpContent.substring(6);
                    imageModule.setStatus("0");
                    imageModule.setStatusInfo("wait");
                    imageModule.setId(Integer.valueOf(imageId));
                    logger.info("具体组装的一个imageModule对象信息,将其放入集合中，最后插入到具体模块表中，" +
                            "状态status=0.status_info = 'wait'"+imageModule.toString());
                    imageModuleLists.add(imageModule);

                }
                for(ImageModule imageModule : imageModuleLists){
                    //获取模块表中Id数据，是否已经插入过此数据，已经有了跳过，没有就新增，I的唯一
                    ImageModule imageModuleExtis = imageModuleMapper.getImageModuleById(imageModule.getId());
                    logger.info("获取模块表中Id数据，是否已经插入过此数据，已经有了跳过，没有就新增，I的唯一"+imageModuleExtis);
                    if(imageModuleExtis == null || imageModuleExtis.getId() == null){
                        logger.info("imageModule不存在，进行添加"+imageModule.toString());
                        imageModuleMapper.insertImageModule(imageModule);
                    }else{
                        logger.info("imageModule信息存在,且存在信息为："+imageModuleExtis.toString());
                    }

                }
            } catch (IOException e) {
                logger.info("异常信息"+e.getMessage());
                logger.info("常见异常信息，访问超时");
            }
        }

        /**
         * 访问图片首页链接地址
         * @param moduleName
         * @return
         */
        public List<ImageModule> getImageModule(String moduleName) {
            List<ImageModule> imageModules = imageModuleMapper.getImageModulesByImageModule(moduleName);
            imageModules.forEach(data->{
                logger.info("获取的状态为wait并且status=0的记录，并且module_name是"+moduleName+"模块的信息" + data);
            });
            return imageModules;
        }

        /**
         * 获取图片地址的方法
         * @param imageUrl
         * @param id
         * @param moduleName
         * @param imageName
         */
        public void getImageSourceUrl(String imageUrl,int id,String moduleName,String imageName){

            try {
                Document document = null;
                logger.info("开始访问图片首页地址"+imageUrl);
                document = Jsoup.connect(imageUrl).get();
                logger.info("访问到的document"+document.toString());
                //  System.out.println(document);
                Elements elements = document.getElementsByTag("img");
                for (Element element:elements){
                    ImageModuleSource imageModuleSource = new ImageModuleSource();
                    System.out.println(element);
                    imageModuleSource.setId(id);
                    if(element.toString().contains("http")) {
                        String imageSourceUrl = element.toString().substring(10,element.toString().indexOf("jpg")+3);
                        imageModuleSource.setImageModule(moduleName);
                        imageModuleSource.setImageSourceUrl(imageSourceUrl);
                        imageModuleSource.setHttpUrl(imageUrl);
                        imageModuleSource.setImageName(imageName);
                        logger.info("分析出来的具体需要插入的图片对象"+imageModuleSource.toString());
                        imageModuleSourceMapper.insertImageModuleSource(imageModuleSource);

                    }
                }
            } catch (IOException e) {
                logger.info("图片插入失败，具体信息"+e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * 给首页信息表中添加信息，供给展示首页信息
         * @param moduleName
         */
        public void insertIntoImagePathIndexImage(String moduleName) {
            logger.info("insertIndexImage");
            //从image_module中获取所有的数据，主要是为了Id和module_name，此处貌似应该有个字典表才好
            List<ImageModule> imageModulesByImageModule =
                    imageModuleMapper.getImageModuleByImageModuleIndex(moduleName);
            //插入到index表中
            logger.info("获取到的信息imageModulesByImageModule" + imageModulesByImageModule.toString());
            for (ImageModule imageModule : imageModulesByImageModule) {
                int id = imageModule.getId();
                //去查询image_module_source
                ImagePath imagePath = imagePathMapper.getImagePathById(id);
                logger.info("这是获取到的单个欢迎图片信息" + imagePath);
                ImagePathIndex imagePathIndex = new ImagePathIndex();
                imagePathIndex.setId(imagePath.getId());
                imagePathIndex.setImageName(imagePath.getImageName());
                imagePathIndex.setImagePath(imagePath.getImagePath());
                imagePathIndex.setModuleName(imagePath.getModuleName());
                ImagePathIndex imagePathIndexExtis = imagePathIndexMapper.getImagePathInfoByIf(imagePathIndex.getId());
                logger.info("是否存在" + imagePathIndexExtis);

                if (imagePathIndexExtis == null) {
                    logger.info("插入" + imagePathIndex.toString());
                    imagePathIndexMapper.insertImagePathIndexInfo(imagePathIndex);
                } else {
                    logger.info("已经存在这个首页图片了，不需要在执行了");
                }
            }
        }

    /**
     * 这是在image_module中查询为访问地址，
     * 访问后将数据放入到image_module_source中去
        然后修改原来的image_moduel中的访问地址的status=1 and status_info = 'Success'
     * @param module
     */
    public void getImageSourceUrlToinsert(String module) {
        List<ImageModule> imageModules = getImageModule(module);
        for (ImageModule imageModule:imageModules){
            int id = imageModule.getId();
            String imageUrl = imageModule.getSourceImage();
            String moduleName = imageModule.getModuleName();
            String imageName = imageModule.getImageName();
            getImageSourceUrl(imageUrl,id,moduleName,imageName);
            imageModuleMapper.updateImageModuleById(id);
        }
    }
}

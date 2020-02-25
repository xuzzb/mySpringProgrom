package com.dcits.handler.image;

import com.dcits.dao.ImageModuleMapper;
import com.dcits.dao.ImageModuleSourceMapper;
import com.dcits.dao.ImageSourceIndexMapper;
import com.dcits.dao.VisitModuleUrlMapper;
import com.dcits.entity.ImageModule;
import com.dcits.entity.ImageModuleSource;
import com.dcits.entity.ImageSourceIndex;
import com.dcits.entity.VisitModuleUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/19 19:46
 * @Version 1.0
 * @Since study
 * 亚洲图片信息处理完成，其他的图片信息处理，应该也是这个流程
 */
@Component
public class AsianPhotoHandler {
    private final Logger logger = LoggerFactory.getLogger(AsianPhotoHandler.class);
    @Resource
    private ImageModuleMapper imageModuleMapper;

    @Resource
    private VisitModuleUrlMapper visitModuleUrlMapper;

    @Resource
    private ImageModuleSourceMapper imageModuleSourceMapper;
    @Resource
    private ImageSourceIndexMapper imageSourceIndexMapper;
    /**
     * 此处去访问网站图片模块，然后处理结果，将图片模块首页中的地址米放入表中
     * 表中会存储这个地址的访问状态和结果，
     * 后边会根据这个信息，访问每一个首页中的链接
     * 获取链接中的图片地址，保存在数据库中，便于使用
     * @param
     * @return
     */
    @Scheduled(cron="*/10 * * * * ?")
    @Transactional
    public void getImagesMap(){
        try {
            VisitModuleUrl visitModuleUrl = visitModuleUrlMapper.selectPhotoImageById(1);
            String url = visitModuleUrl.getModuleUrl();
            logger.info("获取到的地址URL"+url);
            List<ImageModule> imageModuleLists = new ArrayList<ImageModule>();
            Document document = null;
            document = Jsoup.connect(url).get();
            logger.info("创建Document,并且获取到Document的信息"+document);
            Elements elements = document.getElementsByAttributeValueContaining("href","/ikfl/");
            for (Element element:elements){
                ImageModule imageModule = new ImageModule();
                imageModule.setImageName(element.text());
                imageModule.setModuleName("asianPhoto");
                imageModule.setImageModule("1");
                String httpContent = element.toString().substring(9,21);
                String httpUrl = "http://www.mmff72.com"+element.toString().substring(9,26) ;
                imageModule.setSourceImage(httpUrl);
                imageModule.setHttpUrl(url);
                String imageId = httpContent.substring(6);
                imageModule.setStatus("0");
                imageModule.setStatusInfo("wait");
                imageModule.setId(Integer.valueOf(imageId));
                logger.info("具体组装的一个imageModule对象信息"+imageModule.toString());
                imageModuleLists.add(imageModule);
            }
            for(ImageModule imageModule : imageModuleLists){
                ImageModule imageModuleExtis = imageModuleMapper.getImageModuleById(imageModule.getId());
                System.out.println(imageModuleExtis);
                logger.info("将imageModule信息添加到表中，如果存在就不添加");
                if(imageModuleExtis == null || imageModuleExtis.getId() == null){
                    logger.info("imageModule不存在，进行添加"+imageModule.toString());
                    imageModuleMapper.insertImageModule(imageModule);
                }else{
                    logger.info("imageModule信息存在"+imageModuleExtis.toString()+"=============>>" +
                            imageModule.toString());
                    System.out.println("存在则不插入");
                }

            }
        } catch (IOException e) {
            logger.info("异常信息"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 根据首页获取的信息，查询数据库的表，得到一条记录，然后去访问网址，获得其中的图片地址
     * 将图片地址放入数据库中
     * 修改主表中的状态为Success
     * 获取失败则更改我False;
     * 并且会失败重试；
     * 此处应该在添加一个失败重试次数，最多重试五次，超过五次就视为失败，最后
     * 集中同意处理
     */
  @Scheduled(cron="*/20 * * * * ?")
    public void getImageSourceUrl(){
        //1、查询数据库，获取AsianPhoto的需要处理的数据列表
        List<ImageModule> imageModules = imageModuleMapper.getImageModulesByImageModule();
        imageModules.forEach(data->{
            logger.info("获取的状态为wait并且status=0的记录，并且module_name是AsianPoto模块的信息" + data);
        });

        for (ImageModule imageModule:imageModules){
            int id = imageModule.getId();
            String imageUrl = imageModule.getSourceImage();
            String moduleName = imageModule.getModuleName();
            String imageName = imageModule.getImageName();
            getImageSourceUrl(imageUrl,id,moduleName,imageName);
            imageModuleMapper.updateImageModuleById(id);
        }


        //2、开始每一条处理，
        //3、修改状态

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
    @Scheduled(cron="*/120 * * * * ?")
    public void insertIndexImage(){
      logger.info("insertIndexImage");
        //从source表中查询
        List<ImageModule> imageModulesByImageModule =
                imageModuleMapper.getImageModuleByImageModuleIndex();
        //插入到index表中
        logger.info("获取到的信息imageModulesByImageModule"+imageModulesByImageModule.toString());
        for(ImageModule imageModule:imageModulesByImageModule){
            int id = imageModule.getId();
            //去查询image_module_source
            ImageModuleSource imageModuleSource = imageModuleSourceMapper.getImageModuleSourceById(id);
            logger.info("这是获取到的单个欢迎图片信息"+imageModuleSource);
            ImageSourceIndex imageSourceIndex = new ImageSourceIndex();
            imageSourceIndex.setHttpUrl(imageModuleSource.getHttpUrl());
            imageSourceIndex.setId(imageModuleSource.getId());
            imageSourceIndex.setImageModule(imageModuleSource.getImageModule());
            imageSourceIndex.setImageName(imageModuleSource.getImageName());
            imageSourceIndex.setImageSourceUrl(imageModuleSource.getImageSourceUrl());

            ImageSourceIndex imageSourceIndexExtis = imageSourceIndexMapper.getImageSourceById(imageSourceIndex.getId());
            logger.info("是否存在"+imageSourceIndexExtis);

            if(imageSourceIndexExtis == null){
                logger.info("插入"+imageSourceIndex.toString());
                imageSourceIndexMapper.insertImageSourceIndex(imageSourceIndex);
            }else{
                logger.info("已经存在这个首页图片了，不需要在执行了");
            }
        }
    }




}

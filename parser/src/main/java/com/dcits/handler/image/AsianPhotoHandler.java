package com.dcits.handler.image;

import com.dcits.dao.ImageModuleMapper;
import com.dcits.dao.ImageModuleSourceMapper;
import com.dcits.dao.VisitModuleUrlMapper;
import com.dcits.entity.ImageModule;
import com.dcits.entity.ImageModuleSource;
import com.dcits.entity.VisitModuleUrl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
 */
@Component
public class AsianPhotoHandler {
    @Resource
    private ImageModuleMapper imageModuleMapper;

    @Resource
    private VisitModuleUrlMapper visitModuleUrlMapper;

    @Resource
    private ImageModuleSourceMapper imageModuleSourceMapper;
    /**
     * 此处去访问网站，然后处理结果
     * 将获取到的亚洲图片，首页信息，保存在数据库中
     * 后边会根据这个信息，访问每一个首页中的链接
     * 获取链接中的图片地址，保存在数据库中，便于使用
     * @param
     * @return
     */
    @Scheduled(cron="*/100 * * * * ?")
    @Transactional
    public void getImagesMap(){
        try {
            VisitModuleUrl visitModuleUrl = visitModuleUrlMapper.selectPhotoImageById(1);
            String url = visitModuleUrl.getModuleUrl();
            List<ImageModule> imageModuleLists = new ArrayList<ImageModule>();
            Document document = null;
            document = Jsoup.connect(url).get();
            //  System.out.println(document);
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
                imageModuleLists.add(imageModule);
            }
            for(ImageModule imageModule : imageModuleLists){
                imageModuleMapper.insertImageModule(imageModule);
            }
            getImageSourceUrl();
        } catch (IOException e) {
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
    public void getImageSourceUrl(){
        //1、查询数据库，获取AsianPhoto的需要处理的数据列表
        List<ImageModule> imageModules = imageModuleMapper.getImageModulesByImageModule();
        for (ImageModule imageModule:imageModules){
            int id = imageModule.getId();
            String imageUrl = imageModule.getSourceImage();
            String moduleName = imageModule.getModuleName();
            String imageName = imageModule.getImageName();
            getImageSourceUrl(imageUrl,id,moduleName,imageName);
        }


        //2、开始每一条处理，
        //3、修改状态

    }

    public void getImageSourceUrl(String imageUrl,int id,String moduleName,String imageName){

        try {
            Document document = null;
            document = Jsoup.connect(imageUrl).get();
            //  System.out.println(document);
            Elements elements = document.getElementsByTag("img");
            for (Element element:elements){
                ImageModuleSource imageModuleSource = new ImageModuleSource();
                System.out.println(element);
                imageModuleSource.setId(id);
                if(element.toString().contains("http")) {
                    String imageSourceUrl = element.toString().substring(10, 89);

                    imageModuleSource.setImageModule(moduleName);
                    imageModuleSource.setImageSourceUrl(imageSourceUrl);
                    imageModuleSource.setHttpUrl(imageUrl);
                    imageModuleSource.setImageName(imageName);
                    imageModuleSourceMapper.insertImageModuleSource(imageModuleSource);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

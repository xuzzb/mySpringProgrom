package com.dcits;

import com.dcits.dao.ImageModuleMapper;
import com.dcits.dao.VisitModuleUrlMapper;
import com.dcits.entity.ImageModule;
import com.dcits.entity.ImageModuleSource;
import com.dcits.entity.VisitModuleUrl;
import com.dcits.handler.image.inter.impl.ImageHandlerInterfaceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/21 22:04
 * @Version 1.0
 * @Since study
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageParserTest {
    @Resource
    private ImageModuleMapper imageModuleMapper;

    @Resource
    private VisitModuleUrlMapper visitModuleUrlMapper;

    @Test
    public void test() throws IOException {
        String url = "http://www.mmff72.com/pxzj/7.html";
        List<ImageModule> imageModuleLists = new ArrayList<ImageModule>();
        Document document =  Jsoup.connect(url).get();
        //  System.out.println(document);
        Elements elements = document.getElementsByAttributeValueContaining("href","/ikfl/");
        for (Element element:elements){
            ImageModule imageModule = new ImageModule();
            String content = element.text();
            System.out.println("这是imageName"+content);
            imageModule.setImageName(content);
            String asianPhotoName = "asianPhoto";
            System.out.println("这是模块名称"+asianPhotoName);
            imageModule.setModuleName(asianPhotoName);
            String asianPhotoId = "1";
            System.out.println("这是模块标识"+asianPhotoId);
            imageModule.setImageModule(asianPhotoId);
            String httpContent = element.toString().substring(9,21);
            String httpUrl = "http://www.mmff72.com"+element.toString().substring(9,26) ;
            System.out.println("这是Image_Source"+httpUrl);
            imageModule.setSourceImage(httpUrl);
            System.out.println("这是ImageURL"+url);
            imageModule.setHttpUrl(url);
            System.out.println(httpContent);
            String imageId = httpContent.substring(6);
            System.out.println("这是imageId"+imageId);
            imageModule.setId(Integer.valueOf(imageId));
            imageModuleLists.add(imageModule);
        }
        for(ImageModule imageModule : imageModuleLists){
            imageModuleMapper.insertImageModule(imageModule);
        }
    }



    @Test
    public void testVisitModuleUrl(){

        VisitModuleUrl visitModuleUrl = visitModuleUrlMapper.selectPhotoImageById(1);
        System.out.println(visitModuleUrl.toString());

        String url = visitModuleUrl.getModuleUrl();
        System.out.println(url);
    }


    @Test
    public void testGetImageModule(){

    }

    @Test
    public void getImageSourceUrl(){
        try {

            String url = "http://www.mmff72.com/ikfl/850435.html";
            Document document = null;
            document = Jsoup.connect(url).get();
            //  System.out.println(document);
            Elements elements = document.getElementsByTag("img");
            for (Element element:elements){
                ImageModuleSource imageModuleSource = new ImageModuleSource();
                int id = 1111;
                imageModuleSource.setId(id);
                System.out.println(element);
                String src = element.toString().substring(10,89);
                System.out.println(src);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private ImageHandlerInterfaceImpl imageHandlerInterfaceImpl;
    /**
     * 此处去访问网站亚洲图片模块，并且将首页的链接地址放在image_module表中
     * @param
     * @return
     */
    @Test
    public void getImagesMap(){
        int id = 1;
        String moduleName = "asianPhoto";
        System.out.println("dddddddddddddddddddddd");
        imageHandlerInterfaceImpl.getImagesMap(id,moduleName);
    }

}

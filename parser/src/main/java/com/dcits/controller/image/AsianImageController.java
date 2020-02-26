package com.dcits.controller.image;

import com.dcits.dao.ImagePathIndexMapper;
import com.dcits.dao.ImagePathMapper;
import com.dcits.dao.ImageSourceIndexMapper;
import com.dcits.entity.ImagePath;
import com.dcits.entity.ImagePathIndex;
import com.dcits.entity.ImageSourceIndex;
import com.dcits.handler.image.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Author xuzzhh
 * @Date 2020/2/19 20:12
 * @Version 1.0
 * @Since study
 */
@Controller
@RequestMapping("/image")
public class AsianImageController {
    @Resource
    private ImageSourceIndexMapper imageSourceIndexMapper;


    @Resource
    private ImagePathMapper imagePathMapper;

    @Resource
    private ImagePathIndexMapper imagePathIndexMapper;

    private static Logger logger = LoggerFactory.getLogger(AsianImageController.class);

    @RequestMapping("/asianPhoto")
    public String asianPhoto(Model model,int indexId){
        indexId = indexId * 10;
        //获取首页需要展示的数据
        List<ImagePathIndex> imageSourceIndexLis = imagePathIndexMapper.getImagePathIndexList(indexId);
        Map<String,String> imageMap = new ConcurrentHashMap<>();
        for(ImagePathIndex imagePath:imageSourceIndexLis){
         //   System.out.println("获取到的图片内容——————info"+imagePath.toString());
            String imageName =imagePath.getId()+"inform"+imagePath.getImagePath().substring(20,40);
            String imageUrl = imagePath.getImagePath();
           // System.out.println("imageName"+imageName);
            //System.out.println("imageUrl"+imageUrl);
            imageMap.put(imageName+"",imageUrl);

            //System.out.println("imageMap==>>>>>>>>>>>>>>>>"+imageMap);
            //System.out.println(imagePath.toString());
        }
        //System.out.println("map==================="+imageMap.toString());
        model.addAttribute("imageMaps",imageMap);
        //System.out.println("print");
        return "page/image/asian/galler";
    }

    @RequestMapping("/asianPhotoInfo")
    public String asianPhotoInfo(Model model,String idString){
        int id = Integer.parseInt(idString.substring(1,7));
        //去数据库查询详细信息
        List<ImagePath> imagePathInfos = imagePathMapper.getImagePathInfoById(id);
        Map<String,String> imageMap = new ConcurrentHashMap<>();
        for(ImagePath imagePath:imagePathInfos){
            System.out.println("获取到的图片内容——————info"+imagePath.toString());
            String imageName =imagePath.getId()+"inform"+imagePath.getImagePath().substring(20,40);
            String imageUrl = imagePath.getImagePath();
            System.out.println("imageName"+imageName);
            System.out.println("imageUrl"+imageUrl);
            imageMap.put(imageName+"",imageUrl);
            System.out.println("imageMap==>>>>>>>>>>>>>>>>"+imageMap);
            System.out.println(imagePath.toString());
        }
        System.out.println("map==================="+imageMap.toString());
        model.addAttribute("imageMaps",imageMap);
        System.out.println("print");
        return "page/image/asian/gallerinfo";
    }
}

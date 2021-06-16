package com.dcits.controller.image;

import com.dcits.dao.ImagePathIndexMapper;
import com.dcits.dao.ImagePathMapper;
import com.dcits.entity.ImagePath;
import com.dcits.entity.ImagePathIndex;
import com.dcits.entity.IndexModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
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
@RequestMapping("/asian")
public class AsianImageController {
    @Resource
    private ImagePathMapper imagePathMapper;
    @Resource
    private ImagePathIndexMapper imagePathIndexMapper;
    private static Logger logger = LoggerFactory.getLogger(AsianImageController.class);
    //AsianPhoto模块首页展示的模块图片
    @RequestMapping("/asianPhoto")
    public String asianPhoto(Model model,int indexId){
        indexId = indexId * 10;
        String moduleName = "asianPhoto";
        //获取首页需要展示的数据
        IndexModule indexModule = new IndexModule();
        indexModule.setIndex(indexId);
        indexModule.setModuleName(moduleName);
        List<ImagePathIndex> imageSourceIndexLis = imagePathIndexMapper.
                getImagePathIndexList(indexModule);
        Map<String,String> imageMap = new ConcurrentHashMap<>();
        for(ImagePathIndex imagePath:imageSourceIndexLis){
            String imageName =imagePath.getId()+"inform"+imagePath.getImagePath().substring(20,40);
            String imageUrl = imagePath.getImagePath();
            imageMap.put(imageName+"",imageUrl);
        }
        model.addAttribute("imageMaps",imageMap);
        logger.info("发给前端JSP页面的map对象"+imageMap.toString());
        return "page/image/asian/asian";
    }
    /**
     * 前端点击图片以后展示的详细页面
     * @param model
     * @param idString
     * @return
     */
    @RequestMapping("/asianPhotoInfo")
    public String asianPhotoInfo(Model model,String idString,int indexId){
        int id = Integer.parseInt(idString.substring(0,idString.indexOf("inform")));
        indexId = indexId * 10;
        //去数据库查询详细信息
        System.out.println("这里获取到的Id"+id);
        List<ImagePath> imagePathInfos = imagePathMapper.getImagePathInfoById(id,indexId);
        Map<String,String> imageMap = new ConcurrentHashMap<>();
        for(ImagePath imagePath:imagePathInfos){
            System.out.println("获取到的图片内容——————info"+imagePath.toString());
            String imageName =imagePath.getId()+"inform"+imagePath.getImagePath().substring(20,40);
            String imageUrl = imagePath.getImagePath();
            imageMap.put(imageName+"",imageUrl);
        }
        System.out.println("发送给前端页面的详细AsianPhoto图片Map"+imageMap.toString());
        model.addAttribute("imageMaps",imageMap);
        return "page/image/asian/asianInfo";
    }
}

package com.dcits.controller.image;

import com.dcits.dao.ImagePathMapper;
import com.dcits.dao.ImageSourceIndexMapper;
import com.dcits.entity.ImagePath;
import com.dcits.entity.ImageSourceIndex;
import com.dcits.handler.image.*;
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


/**
 * @Author xuzzhh
 * @Date 2020/2/19 20:12
 * @Version 1.0
 * @Since study
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Resource
    private ImageSourceIndexMapper imageSourceIndexMapper;


    @Resource
    private ImagePathMapper imagePathMapper;

    private static Logger logger = LoggerFactory.getLogger(ImageController.class);

    @RequestMapping("/asianPhoto")
    public String asianPhoto(Model model){

            int indexId = 0;
        //获取首页需要展示的数据
        List<ImagePath> imageSourceIndexLis =
                imagePathMapper.getImagePath(indexId);
        Map<String,String> imageMap = new HashMap<>();
        for(ImagePath imagePath:imageSourceIndexLis){
            imageMap.put(imagePath.getImagePath(),imagePath.getImagePath());
            System.out.println(imagePath.toString());
        }
        model.addAttribute("imageMaps",imageMap);
        System.out.println("print");
        return "page/image/galler";
    }
    public String beautifulPhoto(){
        return "test";
    }
    public String europePhoto(){
        return "test";
    }
    public String legAndWaPhoto(){
        return "test";
    }
    public String myselfPhoto(){
        return "test";
    }









}

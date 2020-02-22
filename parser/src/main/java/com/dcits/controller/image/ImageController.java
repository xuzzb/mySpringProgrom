package com.dcits.controller.image;

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
    private static Logger logger = LoggerFactory.getLogger(ImageController.class);
    @Resource
    AsianPhotoHandler asianPhotoHandler;
//    @Resource
//    BeautifulPhotoHandler beautifulPhotoHandler;
//    @Resource
//    EuropePhotoHandler europePhotoHandler;
//    @Resource
//    LegAndWaPhotoHandler legAndWaPhotoHandler;
//    @Resource
//    MyselfPhotoHandler myselfPhotoHandler;
    @RequestMapping("/asianPhoto")
    public String asianPhoto(Model model, HttpServletRequest request){
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        System.out.println(url);
        Map<String,String> imageMaps = new HashMap<String,String>();

        model.addAttribute("imageMaps",imageMaps);
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

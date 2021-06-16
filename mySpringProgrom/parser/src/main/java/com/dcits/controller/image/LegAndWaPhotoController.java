package com.dcits.controller.image;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xuzzhh
 * @Date 2020/2/19 19:46
 * @Version 1.0
 * @Since study
 */
@RequestMapping("/legAndWa")
@Controller
public class LegAndWaPhotoController {
    @RequestMapping("/legAndWaPhoto")
    public String getLegAndWaPhotoIndex(int indexId){
        return "";
    }
}

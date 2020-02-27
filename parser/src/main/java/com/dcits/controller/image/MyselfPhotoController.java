package com.dcits.controller.image;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xuzzhh
 * @Date 2020/2/19 19:46
 * @Version 1.0
 * @Since study
 */
@RequestMapping("/myself")
public class MyselfPhotoController {

    @RequestMapping("/myselfPhoto")
    public String getMyselfPhotoIndex(int indexId){
        return "";
    }
}

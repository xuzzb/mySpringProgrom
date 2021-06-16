package com.dcits.controller.parser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuzzhh
 * @Date 2020/2/19 9:59
 * @Version 1.0
 * @Since study
 */
@RestController
@RequestMapping("/test")
public class ParserController {


    @RequestMapping("/hello")
    public String parser(){
        return "success";
    }
}

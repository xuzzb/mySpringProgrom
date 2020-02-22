package com.dcits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuzzhh
 * @Date 2020/2/18 16:42
 * @Version 1.0
 * @Since study
 */
@RestController
@SpringBootApplication
@MapperScan("com.dcits")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}

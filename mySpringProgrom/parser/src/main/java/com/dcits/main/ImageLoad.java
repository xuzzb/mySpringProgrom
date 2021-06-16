package com.dcits.main;

import java.io.IOException;

public class ImageLoad {

    public static void main(String[] args) throws IOException {
        String url = "http://p7.urlpic.club/pic1893/upload/image/20190220/22008352148.jpg";
        HttpRequest.downLoadFromUrl(url,"22008352148.jpg","D:\\");
        System.out.println("下载完成");

    }
}
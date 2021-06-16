package com.dcits.main;

import com.dcits.entity.ImageModule;
import com.dcits.entity.Module;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/21 21:43
 * @Version 1.0
 * @Since study
 */
public class parserTest {

    public static void main(String[] args) throws IOException {
        parser("http://www.mmff72.com/pxzj/7.html");
    }
    public static void parser(String url) throws IOException {

        Document document =  Jsoup.connect(url).get();
        //  System.out.println(document);
        Elements elements = document.getElementsByAttributeValueContaining("href","/ikfl/");
        for (Element element:elements){
            String content = element.text();
            System.out.println("这是imageName"+content);
            String asianPhoto = "asianPhoto";
            System.out.println("这是模块名称"+asianPhoto);
            String asianPhotoId = "1";
            System.out.println("这是模块标识"+asianPhotoId);
            String httpContent = element.toString().substring(9,21);
            String httpUrl = "http://www.mmff72.com"+element.toString().substring(9,26) ;
            System.out.println("这是Image_Source"+httpUrl);
            System.out.println("这是ImageURL"+url);
            System.out.println(httpContent);
            String imageId = httpContent.substring(6);
            System.out.println("这是imageId"+imageId);
        }
    }















}

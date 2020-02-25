package com.dcits.main;

import com.dcits.entity.ImageModuleSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author xuzzhh
 * @Date 2020/2/24 21:54
 * @Version 1.0
 * @Since study
 */
public class subMain {

    public static void main(String[] args){
        getImageSourceUrl("http://www.mmff72.com/ikfl/851879.html");
    }
    public static void getImageSourceUrl(String imageUrl){

        try {
            Document document = null;
            document = Jsoup.connect(imageUrl).get();
            //  System.out.println(document);
            Elements elements = document.getElementsByTag("img");
            for (Element element:elements){
                System.out.println(element);
                if(element.toString().contains("http")) {
                    String imageSourceUrl = element.toString().substring(10,element.toString().indexOf("jpg")+3);
                    System.out.println(imageSourceUrl);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

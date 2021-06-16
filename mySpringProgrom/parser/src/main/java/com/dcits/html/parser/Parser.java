package com.dcits.html.parser;

import com.dcits.dao.ModuleMapper;
import com.dcits.entity.Module;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author xuzzhh
 * @Date 2020/2/17 21:23
 * @Version 1.0
 * @Since study
 */
public class Parser {
    public static void main(String[] args) throws IOException {
        Document document =  Jsoup.connect("http://www.mmff72.com/ikfl/849399.html").get();
        System.out.println(document);
    }

}

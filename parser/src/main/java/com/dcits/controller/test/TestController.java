package com.dcits.controller.test;

import com.dcits.dao.ModuleMapper;
import com.dcits.entity.Module;
import com.dcits.html.parser.MyRunnable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/18 16:53
 * @Version 1.0
 * @Since study
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private ModuleMapper moduleMapper;
    @RequestMapping("/test")
    public String test(){
        List<Module> modules = moduleMapper.slectModuleById(850800,850800);
        modules.forEach(module -> {
            try {
                System.out.println(module.toString());
                parser(module.getUrl(),module.getId(),module);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "test";
    }
    public void parser(String url,int id,Module module) throws IOException {
        Document document =  Jsoup.connect(url).get();
        Elements elements = document.getElementsContainingOwnText("您的位置");
        //获取类型
        for(Element element:elements){
            module.setName(element.text());
             Elements elements1 = element.getElementsByAttributeValueContaining("href","/p");
             System.out.println(elements1);
             module.setModule(elements1.toString());
        }
        Elements elementsImgs = document.getElementsByTag("img");
        for(Element elementImg:elementsImgs){
            System.out.println(elementImg);
        }
        //获取其中的链接
    //    System.out.println(document);
        Elements elementMag = document.getElementsContainingOwnText("magnet");
        System.out.println(elementMag);
        module.setSource(elementMag.toString());
        module.setId(id);
        moduleMapper.updateByModuleId(module);

    }
}

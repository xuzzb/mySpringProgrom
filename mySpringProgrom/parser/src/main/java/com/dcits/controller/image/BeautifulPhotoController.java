package com.dcits.controller.image;

import com.dcits.dao.ImagePathIndexMapper;
import com.dcits.dao.ImagePathMapper;
import com.dcits.entity.ImagePath;
import com.dcits.entity.ImagePathIndex;
import com.dcits.entity.IndexModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xuzzhh
 * @Date 2020/2/19 19:46
 * @Version 1.0
 * @Since study
 */
@Controller
@RequestMapping("/beautiful")
public class BeautifulPhotoController {
    @Resource
    private ImagePathMapper imagePathMapper;
    @Resource
    private ImagePathIndexMapper imagePathIndexMapper;
    private static Logger logger = LoggerFactory.getLogger(BeautifulPhotoController.class);
    @RequestMapping("/beautifulPhoto")
    public String getBeautifulPhotoIndex(Model model,int indexId){
        indexId = indexId * 10;
        //获取首页需要展示的数据
        String moduleName = "beautifulPhoto";
        IndexModule indexModule = new IndexModule();
        indexModule.setIndex(indexId);
        indexModule.setModuleName(moduleName);
        List<ImagePathIndex> imageSourceIndexLis = imagePathIndexMapper.
                getImagePathIndexList(indexModule);
        Map<String,String> imageMap = new ConcurrentHashMap<>();
        for(ImagePathIndex imagePath:imageSourceIndexLis){
            String url = imagePath.getImagePath();
            String imageName =imagePath.getId()+"inform"+imagePath.getImagePath().substring(url.lastIndexOf("/")+1,url.lastIndexOf("jpg")+3);
            String imageUrl = imagePath.getImagePath();
            imageMap.put(imageName+"",imageUrl);
        }
        model.addAttribute("imageMaps",imageMap);
        logger.info("发给前端JSP页面的map对象"+imageMap.toString());
        return "page/image/beautiful/beautiful";
    }
    /**
     * 前端点击图片以后展示的详细页面
     * @param model
     * @param idString
     * @return
     */
    @RequestMapping("/beautifulPhotoInfo")
    public String asianPhotoInfo(Model model, String idString,int indexId){
        int id = Integer.parseInt(idString.substring(0,idString.indexOf("inform")));
        indexId = indexId * 10;
        System.out.println("id============>>>"+id);
        //去数据库查询详细信息
        List<ImagePath> imagePathInfos = imagePathMapper.getImagePathInfoById(id,indexId);
        //但是此处需要进行分页
        Map<String,String> imageMap = new ConcurrentHashMap<>();
        for(ImagePath imagePath:imagePathInfos){
            String url = imagePath.getImagePath();
            logger.info("url======>>>"+url);
            String imageName =imagePath.getId()+"inform"+
                    imagePath.getImagePath().
                            substring(url.lastIndexOf("/")+1,url.lastIndexOf("jpg")+3);
            String imageUrl = imagePath.getImagePath();
            imageMap.put(imageName+"",imageUrl);
        }
        System.out.println("发送给前端页面的详细AsianPhoto图片Map"+imageMap.toString());
        model.addAttribute("imageMaps",imageMap);
        model.addAttribute("indexId",id);
        return "page/image/beautiful/beautifulInfo";
    }
}

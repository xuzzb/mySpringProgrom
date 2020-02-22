package com.dcits.html.parser;

import com.dcits.dao.ModuleMapper;
import com.dcits.entity.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author xuzzhh
 * @Date 2020/2/18 20:21
 * @Version 1.0
 * @Since study
 */
public class MyRunnable implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(MyRunnable.class);
    private ModuleMapper moduleMapper;
    private int start;
    private int end;
    public MyRunnable(ModuleMapper moduleMapper,int start,int end){
        this.moduleMapper = moduleMapper;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        Module module = new Module();
        for (int i = start;i<end;i++) {
            String url = "http://www.mmff72.com/ikfl/"+i+".html";
            module.setId(i);
            module.setUrl(url);
            logger.info(i+"insert sql " + url );
            moduleMapper.insertModule(module);
        }
    }
}

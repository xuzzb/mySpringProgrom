package com.dcits.handler.image;

import com.dcits.dao.ImageModuleSourceMapper;
import com.dcits.dao.ImagePathMapper;
import com.dcits.entity.ImageModuleSource;
import com.dcits.entity.ImagePath;
import com.dcits.main.ImageMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @Author xuzzhh
 * @Date 2020/2/25 20:26
 * @Version 1.0
 * @Since study
 */
@Component
public class ImageDownLoadHandler {
    @Resource
    private ImageModuleSourceMapper imageModuleSourceMapper;

    @Resource
    private ImagePathMapper imagePathMapper;
    private final Logger logger = LoggerFactory.getLogger(ImageDownLoadHandler.class);

    static
    {
        try
        {
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                                                              public boolean verify(String urlHostName, SSLSession session)
                                                              {
                                                                  return true;
                                                              }
                                                          }
            );
        } catch (Exception e)  {}
    }
    private static void trustAllHttpsCertificates()
            throws NoSuchAlgorithmException, KeyManagementException
    {
        TrustManager[] trustAllCerts = new TrustManager[1];
        trustAllCerts[0] = new ImageMain.TrustAllManager();
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(
                sc.getSocketFactory());
    }

    private static class TrustAllManager
            implements X509TrustManager
    {
        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }
        public void checkServerTrusted(X509Certificate[] certs,
                                       String authType)
                throws CertificateException
        {
        }
        public void checkClientTrusted(X509Certificate[] certs,
                                       String authType)
                throws CertificateException
        {
        }
    }
    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.out.println("开始下载图片");
            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(buffer);//返回Base64编码过的字节数组字符串
            System.out.println(encode);
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时任务，去查询表中路径，image_module_source然后下载图片
     */
    //@Scheduled(cron="*/20 * * * * ?")
    public void getImageSource(){
        List<ImageModuleSource> imageModuleSources =
                imageModuleSourceMapper.getImageModuleSourceList();
        for(ImageModuleSource imageModuleSource:imageModuleSources){
            logger.info("获取到的信息imageModuleSource" + imageModuleSource);
            String url = imageModuleSource.getImageSourceUrl();
            String path = "D:\\mySpringBootProgrom\\parser\\src\\main\\resources\\static\\image\\"+
                    imageModuleSource.getImageModule()+"\\";
            String imageName = url.substring(url.lastIndexOf("/")+1,url.indexOf("jpg")+3);
            String imagePath = path + imageName;
            System.out.println("获取到的图片地址"+imagePath);
            downloadPicture(url,imagePath);
            //将图片地址入库
            ImagePath imagePathInfo = new ImagePath();
            imagePathInfo.setId(imageModuleSource.getId());
            imagePathInfo.setImageName(imageModuleSource.getImageName());
            imagePathInfo.setModuleName(imageModuleSource.getImageModule());
            imagePathInfo.setImagePath("../image/"+imageModuleSource.getImageModule()+"/"+imageName);
            imagePathMapper.insertImagePathInfo(imagePathInfo);
            imageModuleSourceMapper.updateDownLoadStatusByName(imageModuleSource.getImageSourceUrl());
        }
    }
}

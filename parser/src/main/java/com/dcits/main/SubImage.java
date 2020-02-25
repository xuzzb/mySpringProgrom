package com.dcits.main;

/**
 * @Author xuzzhh
 * @Date 2020/2/25 20:31
 * @Version 1.0
 * @Since study
 */
public class SubImage {
    public static void main(String[] args){
        String url = "https://i.imagseur.com/uploads/2020-02/17/7e22040c2895f7f2b85d044d4d946a16.jpg";

        String path = url.substring(url.lastIndexOf("/")+1,url.indexOf(".jpg")+3);

        System.out.println(path);

    }
}

package com.dcits.main;

import sun.misc.BASE64Encoder;

import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author xuzzhh
 * @Date 2020/2/25 20:02
 * @Version 1.0
 * @Since study
 */
public class ImageMain {
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
        trustAllCerts[0] = new TrustAllManager();
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(
                sc.getSocketFactory());
    }

    public static class TrustAllManager
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
    public static void main(String[] args){
        String url = "https://i.imagseur.com/uploads/2020-02/20/c40c00dd6e26e23613b98efad70c8291.jpg";
        String path = "D:/test.jpg";
        downloadPicture(url,path);
    }
    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

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
}

package com.liqihua.camera.demo.common;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * @author liqihua
 * @since 2018/5/27
 */
public class Tool {


    /**
     * 把base64图片保存为file
     * @param base64
     * @return
     */
    public static String saveBase64Img(String base64){
        try {
            String dir = getProjectPath()+"upload/";
            String fileName = System.currentTimeMillis()+".png";
            String path = dir+fileName;
            File saveDir = new File(dir);
            saveDir.mkdirs();
            System.out.println("dir:"+dir);
            System.out.println("path:"+path);

            Files.write(Paths.get(path), Base64.getDecoder().decode(base64), StandardOpenOption.CREATE);
            return "/upload/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    /**
     * 获取项目路径
     */
    public static String getProjectPath() {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        if(path.contains("!")) {
            path = path.substring(0, path.indexOf("!"));
            path = path.replace("file:", "");
            path = path.substring(0,path.lastIndexOf("/")+1);
        }else {
            path = path.substring(1);
            path = path.replace("classes/", "");
        }

        if(isWindows()){
            String firstChar = path.substring(0,1);
            if(firstChar.equals("/")){
                path = path.substring(1,path.length());
            }
        }
        return path;
    }


    /**
     * 判断是否linux系统
     * @return
     */
    public static boolean isLinux(){
        return osName().indexOf("linux") >= 0;
    }

    /**
     * 判断是否windows系统
     * @return
     */
    public static boolean isWindows(){
        return osName().indexOf("win") >= 0;
    }


    /**
     * 获取系统名
     * @return
     */
    public static String osName(){
        return System.getProperty("os.name").toLowerCase();
    }

    public static String getIp(){
        String protocol = "http://";
        String netip = null;
        try{
            netip = protocol+InetAddress.getLocalHost().getHostAddress();
            if(netip == null || netip.isEmpty()){
                netip = protocol + "127.0.0.1";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return netip;
    }

}

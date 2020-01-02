package com.liqihua.camera.demo.controller;

import com.liqihua.camera.demo.common.Tool;
import com.liqihua.camera.demo.config.websocket.WebSocketPool;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author liqihua
 * @since 2018/5/27
 */
@RestController
@RequestMapping("/cameraController")
public class CameraController {
    @Resource
    private Environment environment;

    /**
     * pc端传送文件
     * @param img
     * @return
     */
    @RequestMapping("/receiveImg")
    public String receiveImg(String img){
        System.out.println("receiveImg:");
        System.out.println(img);
        img = img.replace("data:","").replace("image/png;","").replace("base64,","");
        String path = Tool.saveBase64Img(img);
        String url = Tool.getIp() + ":" + environment.getProperty("server.port") + path;
        try {
            WebSocketPool.clientList.forEach(s -> {
                try {
                    s.sendMessage(new TextMessage(url));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "receiveImg";
    }


    /**
     * client端点击拍照
     * @return
     */
    @RequestMapping("/shot")
    public String shot(HttpServletRequest request){
        WebSocketPool.pcList.forEach(s->{
            try {
                s.sendMessage(new TextMessage("shot"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "shot";
    }



}

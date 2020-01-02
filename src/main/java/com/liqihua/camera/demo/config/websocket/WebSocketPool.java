package com.liqihua.camera.demo.config.websocket;

import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedList;
import java.util.List;

/**
 * @author liqihua
 * @since 2018/5/27
 */
public class WebSocketPool {

    public static List<WebSocketSession> pcList = new LinkedList<>();
    public static List<WebSocketSession> clientList = new LinkedList<>();
}

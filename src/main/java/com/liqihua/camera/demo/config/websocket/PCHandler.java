package com.liqihua.camera.demo.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author liqihua
 * @since 2018/5/27
 */

public class PCHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("--- afterConnectionEstablished");
        session.sendMessage(new TextMessage("Established"));
        WebSocketPool.pcList.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("--- handleTextMessage "+message.getPayload());
        super.handleTextMessage(session, message);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("--- afterConnectionClosed");
        WebSocketPool.pcList.remove(session);
        super.afterConnectionClosed(session, status);
    }
}

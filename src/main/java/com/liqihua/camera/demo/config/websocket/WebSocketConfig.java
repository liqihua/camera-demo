package com.liqihua.camera.demo.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author liqihua
 * @since 2018/5/27
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    public PCHandler pcHandler(){
        return new PCHandler();
    }

    @Bean
    public ClientHandler clientHandler(){
        return new ClientHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(pcHandler(),"/camera-server");
        registry.addHandler(clientHandler(),"/getImg");
    }
}

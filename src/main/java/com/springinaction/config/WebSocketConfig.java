package com.springinaction.config;

import com.springinaction.websocket.MarcoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by zjjfly on 2017/3/6.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(socketHandler(),"/marco/test");
        registry.addHandler(socketHandler(),"/marco/test").withSockJS();
    }

    @Bean
    public WebSocketHandler socketHandler(){
        return new MarcoHandler();
    }
}

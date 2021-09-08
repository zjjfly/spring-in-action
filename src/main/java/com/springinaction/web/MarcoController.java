package com.springinaction.web;

import com.springinaction.common.SpittleNotFoundException;
import com.springinaction.websocket.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by zjjfly on 2017/3/7.
 */
@Controller
public class MarcoController {
    private Logger logger= LoggerFactory.getLogger(MarcoController.class);

    @MessageMapping("/marcopolo")
    @SendTo("/topic/shout")
    public Shout handleShout(Shout shout){
        logger.info("Recieve Message:"+shout.getMessage());
        return new Shout("Polo!");
    }

    @SubscribeMapping("/shout")
    public Shout handleSubscription(){
        Shout shout = new Shout("Connected!");
        return shout;
    }

    @MessageExceptionHandler(SpittleNotFoundException.class)
    @SendToUser("/queue/errors")//@SendToUser会发送消息给特定用户而不是所有订阅的用户
    public void handleException(Throwable throwable){
        logger.error("Error handling message:"+throwable.getMessage());
    }
}

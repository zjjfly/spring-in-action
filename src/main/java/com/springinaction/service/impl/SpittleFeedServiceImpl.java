package com.springinaction.service.impl;

import com.springinaction.common.Spittle;
import com.springinaction.service.SpittleFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zjjfly on 2017/3/8.
 */
@Service
public class SpittleFeedServiceImpl implements SpittleFeedService {

    private SimpMessageSendingOperations sendingOperations;

    private Pattern pattern=Pattern.compile("\\@(\\S+)");

    @Autowired
    public SpittleFeedServiceImpl(SimpMessageSendingOperations sendingOperations) {
        this.sendingOperations = sendingOperations;
    }

    @Override
    public void broadcastSpittle(Spittle spittle) {
        sendingOperations.convertAndSend("/topic/spittlefeed",spittle);
        Matcher matcher = pattern.matcher(spittle.getMessage());
        if(matcher.find()){
            String username = matcher.group(1);
            //convertAndSendToUser发送给特定的用户，用户需要订阅/user/queue/notify
            sendingOperations.convertAndSendToUser(username,"/queue/notify",spittle);
        }
    }
}

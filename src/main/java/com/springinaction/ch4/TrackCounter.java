package com.springinaction.ch4;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjjfly on 2016/12/26.
 */
@Component
@Aspect
public class TrackCounter {
    Logger logger= LoggerFactory.getLogger(TrackCounter.class);

    private Map<Integer, Integer> trackCounts = new HashMap();

    @Pointcut("execution(* com.springinaction.ch2.CompactDisc.playTrack(int)) && args(trackNumber)")
    public void trackPlayed(int trackNumber) {
    }

    @Before("trackPlayed(trackNumber)")
    public void countTrack(int trackNumber){
        int playCount=getPlayCount(trackNumber);
        trackCounts.put(trackNumber,playCount+1);
    }
    public int getPlayCount(int trackNumber){
        return  trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber):0;
    }
//拦截带特定注解的方法
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//    public void pointCut(){}
//
//    @Before("pointCut() && @annotation(requestMapping)")
//    public void log(JoinPoint joinPoint,RequestMapping requestMapping){
//        logger.debug(requestMapping.value().toString());
//    }
}

package com.springinaction.ch4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjjfly on 2016/12/26.
 */
public class NewTrackCounter {
    private Map<Integer, Integer> trackCounts = new HashMap<>();

    public void trackPlayed(int trackNumber) {
    }

    public void countTrack(int trackNumber){
        int playCount=getPlayCount(trackNumber);
        trackCounts.put(trackNumber,playCount+1);
    }

    public int getPlayCount(int trackNumber){
        return  trackCounts.containsKey(trackNumber)?trackCounts.get(trackNumber):0;
    }
}

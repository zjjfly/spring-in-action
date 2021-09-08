package com.springinaction.ch4;

import com.springinaction.ch2.CompactDisc;
import com.springinaction.common.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Created by zjjfly on 2016/12/25.
 */
public class AOPTest extends AbstractTest {
    @Autowired
    Performance performance;

    @Autowired
    CompactDisc disc;

    @Autowired
    TrackCounter counter;

    @Autowired
    NewTrackCounter newTrackCounter;

    @Test
    public void performance(){
        performance.perform();
    }

    @Test
    public void playCount() {
        disc.playTrack(1);
        disc.playTrack(1);
        assertEquals(counter.getPlayCount(1),2);
        assertEquals(newTrackCounter.getPlayCount(1),2);
    }

    @Test
    public void introduceTest() {
        Encoreable encoreable = (Encoreable) this.performance;
        encoreable.performanceEncore();
    }
}

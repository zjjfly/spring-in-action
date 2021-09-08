package com.springinaction.ch2;

import com.springinaction.common.AbstractTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

/**
 * Created by zjjfly on 2016/12/18.
 */
public class CDPlayerTest extends AbstractTest{
    @Rule
    public final SystemOutRule log=new SystemOutRule().enableLog().muteForSuccessfulTests();
    @Autowired
    private CompactDisc cd;

    @Autowired
    private MediaPlayer player;

    @Test
    public void cdShouldNotBeNull(){
        assertNotNull(cd);
    }

    @Test
    public void play(){
        player.play();
        assertEquals("Playing Sgt. Pepper's Lonely Hearts Club Band "
                +"by The Beatles\n",log.getLog());
    }
}

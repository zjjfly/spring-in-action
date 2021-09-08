package com.springinaction.ch2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by zjjfly on 2016/12/19.
 */
@Component
public class CDPlayer implements MediaPlayer{
    private CompactDisc cd;

    @Override
    public void play() {
        cd.play();
    }

    @Autowired(required = false)
    public void setCd(CompactDisc cd) {
        this.cd = cd;
    }
}

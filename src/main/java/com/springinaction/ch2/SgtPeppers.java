package com.springinaction.ch2;

import org.springframework.stereotype.Component;

/**
 * Created by zjjfly on 2016/12/18.
 */
@Component
public class SgtPeppers implements CompactDisc {
    private String title = "Sgt. Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }

    @Override
    public void playTrack(int trackNumber) {

    }
}

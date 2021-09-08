package com.springinaction.ch2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by zjjfly on 2016/12/23.
 */
//@Component
public class BlankDisc {
    private String title;
    private String artist;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    //    public BlankDisc(@Value("${disc.title}") String title, @Value("${disc.artist}") String artist) {
//    public BlankDisc(@Value("#{systemProperties['os.name']}") String title, @Value("#{systemProperties['user.name']}") String artist) {
    public BlankDisc(String title,String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "title:"+title+",artist:"+artist;
    }
}

package com.springinaction.websocket;

/**
 * Created by zjjfly on 2017/3/7.
 */
public class Shout {
    public void setMessage(String message) {
        this.message = message;
    }

    public Shout() {
    }

    private String message;

    public Shout(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

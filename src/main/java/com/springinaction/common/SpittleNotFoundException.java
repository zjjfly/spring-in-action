package com.springinaction.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zjjfly on 2017/1/10.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "spittle not found!")
public class SpittleNotFoundException extends RuntimeException {
    private long spittleId;

    public SpittleNotFoundException(long spittleId) {
        this.spittleId = spittleId;
    }

    public long getSpittleId() {
        return spittleId;
    }
}

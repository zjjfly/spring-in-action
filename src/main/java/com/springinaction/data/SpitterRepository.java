package com.springinaction.data;

import com.springinaction.common.Spitter;

/**
 * Created by zjjfly on 2016/12/30.
 */
public interface SpitterRepository {
    Spitter save(Spitter spitter);
    Spitter findByUsername(String username);
}

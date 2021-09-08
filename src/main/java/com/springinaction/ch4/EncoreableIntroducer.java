package com.springinaction.ch4;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Created by zjjfly on 2016/12/26.
 */
//@Component
@Aspect
public class EncoreableIntroducer {
    @DeclareParents(value = "com.springinaction.ch4.Performance+",defaultImpl = EncoreableImpl.class)
    public static Encoreable encoreable;
}

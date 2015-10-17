package com.github.tiggels.craftphysics.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Miles on 10/17/15.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Quaternion {

    float v0() default 0;
    float v1() default 0;
    float v2() default 0;
    float v3() default 0;
}

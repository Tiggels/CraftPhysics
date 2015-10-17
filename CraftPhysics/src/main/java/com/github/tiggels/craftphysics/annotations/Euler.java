package com.github.tiggels.craftphysics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miles on 10/17/15.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Euler {

    float pitch() default 0;
    float yaw() default 0;
    float roll() default 0;
}

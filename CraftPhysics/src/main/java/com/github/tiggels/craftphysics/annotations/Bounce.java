package com.github.tiggels.craftphysics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miles on 10/17/15.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bounce {

    float VelocityX() default -1;
    float VelocityY() default -1;
    float VelocityZ() default -1;
}

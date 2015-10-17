package com.github.tiggels.craftphysics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miles on 10/17/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Size {

    float sizeX() default 1;
    float sizeY() default 1;
    float sizeZ() default 1;
}

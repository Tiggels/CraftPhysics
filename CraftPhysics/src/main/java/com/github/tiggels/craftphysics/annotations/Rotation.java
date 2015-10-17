package com.github.tiggels.craftphysics.annotations;

import javax.vecmath.Quat4f;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miles on 10/17/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rotation {

    Quaternion quaternion() default @Quaternion();
    Euler euler() default @Euler();
}

package com.github.tiggels.craftphysics.annotations.method;

import com.github.tiggels.craftphysics.physics.CraftRigidBody;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Miles on 10/17/15.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnCollision {

    float speedX() default 0;
    float speedY() default 0;
    float speedZ() default 0;
    Class<? extends CraftRigidBody>[] type() default CraftRigidBody.class;
}

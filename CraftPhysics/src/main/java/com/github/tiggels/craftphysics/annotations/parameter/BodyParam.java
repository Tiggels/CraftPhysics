package com.github.tiggels.craftphysics.annotations.parameter;

import com.github.tiggels.craftphysics.physics.CraftRigidBody;
import com.github.tiggels.craftphysics.physics.PhysicsObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Miles on 10/19/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BodyParam {

}
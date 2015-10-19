package com.github.tiggels.craftphysics.annotations.method;

import com.github.tiggels.craftphysics.physics.CraftBoundingBox;
import com.github.tiggels.craftphysics.physics.CraftRigidBody;
import com.github.tiggels.craftphysics.physics.PhysicsObject;

/**
 * Created by Miles on 10/19/15.
 */
public @interface Intersection {
    Class<? extends PhysicsObject>[] value() default CraftBoundingBox.class;
}

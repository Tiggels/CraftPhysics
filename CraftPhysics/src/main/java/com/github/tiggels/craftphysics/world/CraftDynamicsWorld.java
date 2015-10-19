package com.github.tiggels.craftphysics.world;

import com.bulletphysics.collision.broadphase.AxisSweep3;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.github.tiggels.craftphysics.physics.PhysicsObject;
import org.bukkit.World;

import javax.vecmath.Vector3f;

/**
 * Created by Miles on 10/17/15.
 */
public class CraftDynamicsWorld {

    World world;
    DynamicsWorld dynamicWorld;

    public CraftDynamicsWorld(World world, Vector3f gravity, Vector3f bbBoxMin, Vector3f bbBoxMax) {

        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);

        AxisSweep3 overlappingPairCache = new AxisSweep3(bbBoxMin, bbBoxMax);
        SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();

        dynamicWorld = new DiscreteDynamicsWorld(dispatcher, overlappingPairCache, solver, collisionConfiguration);
        dynamicWorld.setGravity(gravity);
        dynamicWorld.getDispatchInfo().allowedCcdPenetration = 0f;
    }

    public void add(PhysicsObject object) {

    }
}

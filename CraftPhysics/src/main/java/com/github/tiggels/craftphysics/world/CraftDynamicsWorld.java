package com.github.tiggels.craftphysics.world;

import com.bulletphysics.collision.broadphase.AxisSweep3;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.InternalTickCallback;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.github.tiggels.craftphysics.annotations.method.OnCollision;
import com.github.tiggels.craftphysics.annotations.method.OnTime;
import com.github.tiggels.craftphysics.annotations.parameter.BodyParam;
import com.github.tiggels.craftphysics.annotations.parameter.LocationParam;
import com.github.tiggels.craftphysics.annotations.parameter.RotationParam;
import com.github.tiggels.craftphysics.annotations.parameter.TimeParam;
import com.github.tiggels.craftphysics.physics.CraftRigidBody;
import com.github.tiggels.craftphysics.physics.PhysicsObject;
import com.sun.jdi.InvocationException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.junit.experimental.theories.internal.ParameterizedAssertionError;

import javax.vecmath.Vector3f;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miles on 10/17/15.
 */
public class CraftDynamicsWorld {

    private World world;
    private DynamicsWorld dynamicWorld;
    private Boolean online = false;

    public CraftDynamicsWorld(World world, Vector3f gravity, Vector3f bbBoxMin, Vector3f bbBoxMax) {

        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);

        AxisSweep3 overlappingPairCache = new AxisSweep3(bbBoxMin, bbBoxMax);
        SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();

        dynamicWorld = new DiscreteDynamicsWorld(dispatcher, overlappingPairCache, solver, collisionConfiguration);
        dynamicWorld.setGravity(gravity);
        dynamicWorld.getDispatchInfo().allowedCcdPenetration = 0f;
    }

    public void add(CraftRigidBody rigidBody) {
        rigidBody.setStartTime(System.currentTimeMillis());
        dynamicWorld.addRigidBody(rigidBody.getBody());
    }

    public void remove(CraftRigidBody rigidBody) {
        dynamicWorld.removeRigidBody(rigidBody.getBody());
    }

    public void Start(Plugin plugin, final int delay, int period) {

        online = true;

        new BukkitRunnable() {

            long time = System.currentTimeMillis();

            public void run() {

                if (!online) {
                    cancel();
                }

                long delta = time - System.currentTimeMillis();

                dynamicWorld.stepSimulation(delta);

                time = System.currentTimeMillis();

                // CALLBACK TESTS

                // ALL OF THIS DOWN TO LINE 196 OR SO IS FOR COLLISION DETECTION AND HANDING
                Dispatcher dispatcher = dynamicWorld.getDispatcher();
                int manifoldCount = dispatcher.getNumManifolds();
                for (int i = 0; i < manifoldCount; i++) {
                    PersistentManifold manifold = dispatcher.getManifoldByIndexInternal(i);

                    boolean hit = false;
                    Vector3f normal = null;
                    for (int j = 0; j < manifold.getNumContacts(); j++) {
                        ManifoldPoint contactPoint = manifold.getContactPoint(j);
                        if (contactPoint.getDistance() < 0.0f) {
                            hit = true;
                            normal = contactPoint.normalWorldOnB;
                            break;
                        }
                    }
                    if (hit) {

                        // The following two lines are optional.
                        RigidBody object1 = (RigidBody)manifold.getBody0();
                        RigidBody object2 = (RigidBody)manifold.getBody1();

                        CraftRigidBody physicsObject1 = (CraftRigidBody)object1.getUserPointer();
                        CraftRigidBody physicsObject2 = (CraftRigidBody)object2.getUserPointer();

                        for (Method method : physicsObject1.getClass().getMethods()) {
                            if (method.isAnnotationPresent(OnCollision.class)) {
                                if (method.isAnnotationPresent(OnTime.class) && physicsObject1.getStartTime() > method.getAnnotation(OnTime.class).value()) {

                                    List<Object> perimeters = new ArrayList<Object>();

                                    for (Parameter parameter : method.getParameters()) {
                                        if (parameter.isAnnotationPresent(BodyParam.class)) {
                                            perimeters.add(physicsObject2);
                                        } else if (parameter.isAnnotationPresent(LocationParam.class)) {
                                            perimeters.add(new Location(world, normal.x, normal.y, normal.z));
                                        } else if (parameter.isAnnotationPresent(TimeParam.class)) {
                                            perimeters.add(time);
                                        } else {
                                            System.err.println("#1210");
                                            throw new ParameterizedAssertionError(
                                                    new Throwable("Problem with parameters in method \"" + method.getName() + "\" in class \"" + physicsObject1.getClass().getName() + "\""), 
                                                    method.getName(), perimeters);
                                        }
                                    }
                                    
                                    try {
                                        method.invoke(physicsObject1, perimeters);
                                    } catch (IllegalAccessException e) {
                                        System.err.println("#1212");
                                        System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + physicsObject1.getClass().getName() + "\"");
                                        e.printStackTrace();
                                        System.err.println("Method call was dropped");
                                        
                                    } catch (InvocationTargetException e) {
                                        System.err.println("#1214");
                                        System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + physicsObject1.getClass().getName() + "\"");
                                        e.printStackTrace();
                                        System.err.println("Method call was dropped");
                                        
                                    }
                                }
                            }
                        }

                        for (Method method : physicsObject2.getClass().getMethods()) {
                            if (method.isAnnotationPresent(OnCollision.class)) {
                                if (method.isAnnotationPresent(OnTime.class) && physicsObject2.getStartTime() > method.getAnnotation(OnTime.class).value()) {

                                    List<Object> perimeters = new ArrayList<Object>();

                                    for (Parameter parameter : method.getParameters()) {
                                        if (parameter.isAnnotationPresent(BodyParam.class)) {
                                            perimeters.add(physicsObject1);
                                        } else if (parameter.isAnnotationPresent(LocationParam.class)) {
                                            perimeters.add(new Location(world, normal.x, normal.y, normal.z));
                                        } else if (parameter.isAnnotationPresent(TimeParam.class)) {
                                            perimeters.add(time);
                                        } else {
                                            System.err.println("#1220");
                                            throw new ParameterizedAssertionError(
                                                    new Throwable("Problem with parameters in method \"" + method.getName() + "\" in class \"" + physicsObject2.getClass().getName() + "\""),
                                                    method.getName(), perimeters);
                                        }
                                    }

                                    try {
                                        method.invoke(physicsObject2, perimeters);
                                    } catch (IllegalAccessException e) {
                                        System.err.println("#1222");
                                        System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + physicsObject2.getClass().getName() + "\"");
                                        e.printStackTrace();
                                        System.err.println("Method call was dropped");

                                    } catch (InvocationTargetException e) {
                                        System.err.println("#1224");
                                        System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + physicsObject2.getClass().getName() + "\"");
                                        e.printStackTrace();
                                        System.err.println("Method call was dropped");

                                    }
                                }
                            }
                        }
                        
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, delay, period);
    }

    public void stop() {
        online = false;
    }
}

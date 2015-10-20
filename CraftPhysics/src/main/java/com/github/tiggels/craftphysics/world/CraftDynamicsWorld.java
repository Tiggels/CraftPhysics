package com.github.tiggels.craftphysics.world;

import com.bulletphysics.collision.broadphase.AxisSweep3;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.github.tiggels.craftphysics.annotations.method.OnCollision;
import com.github.tiggels.craftphysics.annotations.method.OnCreation;
import com.github.tiggels.craftphysics.annotations.method.OnDestroy;
import com.github.tiggels.craftphysics.annotations.method.OnTime;
import com.github.tiggels.craftphysics.physics.CraftRigidBody;
import com.google.common.collect.HashBiMap;
import com.sun.tools.javac.util.Pair;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.junit.experimental.theories.internal.ParameterizedAssertionError;

import javax.vecmath.Tuple2d;
import javax.vecmath.Vector3f;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        for (Method method : rigidBody.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(OnCreation.class)) {
                try {
                    method.invoke(rigidBody, (Object[])null);
                } catch (IllegalAccessException e) {
                    System.err.println("#100");
                    System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + rigidBody.getClass().getName() + "\"");
                    e.printStackTrace();
                    System.err.println("Method call was dropped");

                } catch (InvocationTargetException e) {
                    System.err.println("#110");
                    System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + rigidBody.getClass().getName() + "\"");
                    e.printStackTrace();
                    System.err.println("Method call was dropped");

                }
            }
        }
    }

    public void remove(CraftRigidBody rigidBody) {

        for (Method method : rigidBody.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(OnDestroy.class)) {
                try {
                    method.invoke(rigidBody, (Object[])null);
                } catch (IllegalAccessException e) {
                    System.err.println("#120");
                    System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + rigidBody.getClass().getName() + "\"");
                    e.printStackTrace();
                    System.err.println("Method call was dropped");

                } catch (InvocationTargetException e) {
                    System.err.println("#130");
                    System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + method.getName() +  "\" IN CLASS \"" + rigidBody.getClass().getName() + "\"");
                    e.printStackTrace();
                    System.err.println("Method call was dropped");

                }
            }
        }

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

                List<CollisionObject> csobj = dynamicWorld.getCollisionObjectArray();
                List<CraftRigidBody> rigidBodies = new ArrayList<CraftRigidBody>();
                for (CollisionObject obj : csobj) {
                    rigidBodies.add((CraftRigidBody)obj.getUserPointer());
                }
                List<Pair<Method, ? extends CraftRigidBody>> methods = new ArrayList<Pair<Method, ? extends CraftRigidBody>>();

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

                        RigidBody object1 = (RigidBody)manifold.getBody0();
                        RigidBody object2 = (RigidBody)manifold.getBody1();

                        CraftRigidBody physicsObject1 = (CraftRigidBody)object1.getUserPointer();
                        CraftRigidBody physicsObject2 = (CraftRigidBody)object2.getUserPointer();

                        physicsObject1.addCollidingBody(physicsObject2);
                        physicsObject2.addCollidingBody(physicsObject1);

                        for (Method method : physicsObject1.getClass().getMethods()) {
                            if (method.isAnnotationPresent(OnCollision.class)) {

                                if (    method.getAnnotation(OnCollision.class).speedX() > Math.abs(physicsObject1.getVelcotyX()) &&
                                        method.getAnnotation(OnCollision.class).speedY() > Math.abs(physicsObject1.getVelcotyY()) &&
                                        method.getAnnotation(OnCollision.class).speedZ() > Math.abs(physicsObject1.getVelcotyZ())) {

                                    for (Class<? extends CraftRigidBody> craftRigidBody : method.getAnnotation(OnCollision.class).type()) {
                                        if (craftRigidBody.equals(physicsObject1.getClass())) {
                                            methods.add(new Pair<Method, CraftRigidBody>(method, physicsObject1));
                                        } else if (methods.contains(new Pair<Method, CraftRigidBody>(method, physicsObject1))) {
                                            methods.remove(new Pair<Method, CraftRigidBody>(method, physicsObject1));
                                        }
                                    }

                                } else if (methods.contains(new Pair<Method, CraftRigidBody>(method, physicsObject1))) {
                                    methods.remove(new Pair<Method, CraftRigidBody>(method, physicsObject1));
                                }
                            }
                        }

                        for (Method method : physicsObject2.getClass().getMethods()) {
                            if (method.isAnnotationPresent(OnCollision.class)) {

                                if (    method.getAnnotation(OnCollision.class).speedX() > Math.abs(physicsObject2.getVelcotyX()) &&
                                        method.getAnnotation(OnCollision.class).speedY() > Math.abs(physicsObject2.getVelcotyY()) &&
                                        method.getAnnotation(OnCollision.class).speedZ() > Math.abs(physicsObject2.getVelcotyZ())) {

                                    for (Class<? extends CraftRigidBody> craftRigidBody : method.getAnnotation(OnCollision.class).type()) {
                                        if (craftRigidBody.equals(physicsObject2.getClass())) {
                                            methods.add(new Pair<Method, CraftRigidBody>(method, physicsObject2));
                                        } else if (methods.contains(new Pair<Method, CraftRigidBody>(method, physicsObject2))) {
                                            methods.remove(new Pair<Method, CraftRigidBody>(method, physicsObject2));
                                        }
                                    }

                                } else if (methods.contains(new Pair<Method, CraftRigidBody>(method, physicsObject2))) {
                                    methods.remove(new Pair<Method, CraftRigidBody>(method, physicsObject2));
                                }
                            }
                        }
                    }
                }

                for (CraftRigidBody rigidBody : rigidBodies) {
                    for (Method method : rigidBody.getClass().getDeclaredMethods()) {
                        if (method.isAnnotationPresent(OnTime.class) && method.getAnnotation(OnTime.class).value() < time) {
                            methods.add(new Pair<Method, CraftRigidBody>(method, rigidBody ));
                        } else if (methods.contains(new Pair<Method, CraftRigidBody>(method, rigidBody ))) {
                            methods.remove(new Pair<Method, CraftRigidBody>(method, rigidBody ));
                        }
                    }
                }

                for (Pair<Method, ? extends CraftRigidBody> pair : methods) {
                    try {
                        pair.fst.invoke(pair.snd, (Object[])null);
                    } catch (IllegalAccessException e) {
                        System.err.println("#140");
                        System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + pair.fst.getName() +  "\" IN CLASS \"" + pair.snd.getClass().getName() + "\"");
                        e.printStackTrace();
                        System.err.println("Method call was dropped");

                    } catch (InvocationTargetException e) {
                        System.err.println("#150");
                        System.err.println("ERROR IN CALLBACK REFLECTION IN METHOD \""  + pair.fst.getName() +  "\" IN CLASS \"" + pair.snd.getClass().getName() + "\"");
                        e.printStackTrace();
                        System.err.println("Method call was dropped");

                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, delay, period);
    }

    public void stop() {
        online = false;
    }
}

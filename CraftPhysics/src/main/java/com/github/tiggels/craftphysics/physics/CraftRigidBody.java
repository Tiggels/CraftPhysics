package com.github.tiggels.craftphysics.physics;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.*;
import com.github.tiggels.craftphysics.annotations.method.OnCollision;
import com.github.tiggels.craftphysics.annotations.type.Model;
import com.github.tiggels.craftphysics.annotations.type.Size;
import com.github.tiggels.craftphysics.model.ModelLoader;
import com.github.tiggels.craftphysics.util.ExtraQuatUtil;
import org.bukkit.Location;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miles on 10/17/15.
 */
public class CraftRigidBody extends PhysicsObject {

    private RigidBody body;
    private int size;
    private long startTime;
    private List<CraftRigidBody> collidingBodies = new ArrayList<CraftRigidBody>();

    public CraftRigidBody(float mass, Location location, Vector3f velocity, EulerAngle angel, float restitution, float friction, float linearDamp, float angularDamp) throws Exception {

        // This constructor basically dose all the work
        // It handels the construction of the BulletPhysics RigidBody
        // and takes care of the parameter annotations.

        // This is one busy little Bee

        Quat4f rotation = new Quat4f(0,0,0,0);
        QuaternionUtil.setEuler(rotation, (float) angel.getX(), (float) angel.getY(), (float) angel.getZ());

        ModelLoader loader = new ModelLoader();

        CollisionShape shape;

        // Annotation bit

        Class<CraftRigidBody> obj = CraftRigidBody.class;

        // Loads Model
        if (obj.isAnnotationPresent(Model.class)) {
            Model model = obj.getAnnotation(Model.class);
            shape = loader.loadModel(model.value());
        } else {
            System.err.println("CraftRigidBody had unsigned model and was built with default cube model");
            shape = new BoxShape(new Vector3f(.5f,.5f,.5f));
        }

        // Sets Size
        if (obj.isAnnotationPresent(Size.class)) {
            Size size = obj.getAnnotation(Size.class);
            this.size = size.value();
        } else {
            System.err.println("CraftRigidBody had unsigned model and was built with default cube model");
            shape = new BoxShape(new Vector3f(.5f,.5f,.5f));
        }

        shape.calculateLocalInertia(mass, velocity);

        Transform startTransform = new Transform();
        startTransform.setIdentity();
        startTransform.origin.set(new Vector3f((float) location.getX(),(float) location.getY(),(float) location.getZ()));
        startTransform.basis.set(rotation);

        MotionState ms = new DefaultMotionState(startTransform); //TODO: use possible offset to make json model offset better on armor stands

        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, ms, shape, velocity);
        body = new RigidBody(rbInfo);
        body.setRestitution(restitution);
        body.setFriction(friction);
        body.setDamping(linearDamp, angularDamp);
        body.setUserPointer(this);
    }

    //ROTATION
    public void setRotation(Quat4f quat4f) {
        Transform transform = new Transform();
        body.getWorldTransform(transform);
        transform.basis.set(quat4f);
    }

    public void setRotation(float pitch, float roll, float yaw) {
        Transform transform = new Transform();
        body.getWorldTransform(transform);

        Quat4f quat = new Quat4f();
        QuaternionUtil.setEuler(quat, yaw, pitch, roll);

        transform.basis.set(quat);
    }

    public void setRotation(EulerAngle angle) {
        Transform transform = new Transform();
        body.getWorldTransform(transform);

        Quat4f quat = new Quat4f();
        QuaternionUtil.setEuler(quat, (float) angle.getY(), (float) angle.getZ(), (float) angle.getX()); // EULER ANGEL IS THE WORST THING EVER!!!!!!!!!!!!!!!!!! :[

        transform.basis.set(quat);
    }

    public Quat4f getRotation() {
        Transform transform = new Transform();
        body.getWorldTransform(transform);

        Quat4f quat = new Quat4f();
        transform.getRotation(quat);

        return quat;
    }


    //PITCH
    public void setPitch(float pitch) {

    }

    public float getPitch() {
        Transform transform = new Transform();
        body.getWorldTransform(transform);

        Quat4f quat = new Quat4f();
        transform.getRotation(quat);

        return ExtraQuatUtil.getPitch(quat);
    }


    //YAW
    public void setYaw(float yaw) {
    }

    public float getYaw() {
        Transform transform = new Transform();
        body.getWorldTransform(transform);

        Quat4f quat = new Quat4f();
        transform.getRotation(quat);

        return ExtraQuatUtil.getYaw(quat);
    }


    //ROLL
    public void setRoll() {

    }

    public float getRoll() {
        Transform transform = new Transform();
        body.getWorldTransform(transform);

        Quat4f quat = new Quat4f();
        transform.getRotation(quat);

        return ExtraQuatUtil.getRoll(quat);
    }


    //VELOCITY
    public void setVelocity(Vector3f vector) {

    }

    public void setBukkitVelocity(Vector vector) {

    }

    public Vector getBukkitVelocity() {
        return new Vector();
    }

    public Vector3f getVelocity() {
        return new Vector3f();
    }


    //X VELOCITY
    public void setVelocityX(float x) {

    }

    public float getVelcotyX() {
        return 0;
    }


    //Y VELOCITY
    public void setVelocityY(float y) {

    }

    public float getVelcotyY() {
        return 0;
    }


    //Z VELOCITY
    public void setVelocityZ(float z) {

    }

    public float getVelcotyZ() {
        return 0;
    }


    //LOCATION
    public void setLocation(Vector3f vector) {

    }

    public void setBukkitLocation(Location location) {

    }

    public Vector3f getLocation() {
        return new Vector3f(0,0,0);
    }

    public Location getBukkitLocation() {
        return new Location(world,0,0,0);
    }


    //MASS
    public void setMass(float mass) {

    }

    public float getMass() {
        return 0;
    }

    public RigidBody getBody() {
        return body;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public List<CraftRigidBody> getCollidingBodys() {
        return collidingBodies;
    }

    public void clearCollidingBodys() {
        this.collidingBodies = new ArrayList<CraftRigidBody>();
    }

    public void addCollidingBody(CraftRigidBody body) {
        collidingBodies.add(body);
    }
}

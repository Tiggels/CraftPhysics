package com.github.tiggels.craftphysics.physics;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

import javax.vecmath.Vector3f;

/**
 * Created by Miles on 10/17/15.
 */
public class CraftRigidBody {

    World world;

    //ROTATION
    public void setRotation(float pitch, float roll, float yaw) {

    }

    public void setRotation(EulerAngle angle) {

    }

    public EulerAngle getRotation() {
        return new EulerAngle(0,0,0);
    }


    //PITCH
    public void setPitch(float pitch) {

    }

    public float getPitch() {
        return 0;
    }


    //YAW
    public void setYaw(float yaw) {

    }

    public float getYaw() {
        return 0;
    }


    //ROLL
    public void setRoll() {

    }

    public float getRoll() {
        return 0;
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


    //WORLD
    public void setWorld(World world) {

    }

    public World getWorld() {
        return world;
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
}

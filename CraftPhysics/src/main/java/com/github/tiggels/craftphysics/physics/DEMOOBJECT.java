package com.github.tiggels.craftphysics.physics;

import com.github.tiggels.craftphysics.annotations.method.OnCollision;
import com.github.tiggels.craftphysics.annotations.method.OnIntersection;
import com.github.tiggels.craftphysics.annotations.method.OnTime;
import com.github.tiggels.craftphysics.annotations.type.Model;
import com.github.tiggels.craftphysics.annotations.type.Size;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.util.EulerAngle;

import javax.vecmath.Vector3f;

/**
 * Created by Miles on 10/18/15.
 */

@Model("path")
@Size(1)
public class DemoObject extends CraftRigidBody {

    public DemoObject(float mass, Location location, Vector3f velocity, EulerAngle angel, float restitution, float friction, float linearDamp, float angularDamp) throws Exception {
        super(mass, location, velocity, angel, restitution, friction, linearDamp, angularDamp);
    }

    @OnCollision(speedX = 1, speedY = 1, speedZ = 1, type = {DemoObject.class})
    @OnTime(2)
    public void Collision() {
        Bukkit.broadcastMessage(ChatColor.GOLD + "BOING");
    }

    @OnIntersection({CraftRigidBody.class, CraftBoundingBox.class})
    public void Intersection() {

    }
}

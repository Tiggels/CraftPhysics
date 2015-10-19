package com.github.tiggels.craftphysics.physics;

import com.github.tiggels.craftphysics.annotations.method.Collision;
import com.github.tiggels.craftphysics.annotations.method.Intersection;
import com.github.tiggels.craftphysics.annotations.method.Time;
import com.github.tiggels.craftphysics.annotations.param.BodyParam;
import com.github.tiggels.craftphysics.annotations.param.LocationParam;
import com.github.tiggels.craftphysics.annotations.param.TimeParam;
import com.github.tiggels.craftphysics.annotations.param.VelocityParam;
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
@Size(sizeX = 1, sizeY = 1, sizeZ = 1)
public class DemoObject extends CraftRigidBody {

    public DemoObject(float mass, Vector3f location, Vector3f velocity, EulerAngle angel, float restitution, float friction, float linearDamp, float angularDamp) throws Exception {
        super(mass, location, velocity, angel, restitution, friction, linearDamp, angularDamp);
    }

    @Collision(speedX = 1, speedY = 1, speedZ = 1, type = {DemoObject.class})
    @Time(2)
    public void Collision() {
        Bukkit.broadcastMessage(ChatColor.GOLD + "BOING");
    }

    @Intersection({CraftRigidBody.class, CraftBoundingBox.class})
    public void Intersection() {

    }
}

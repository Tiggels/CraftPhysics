package com.github.tiggels.craftphysics.physics;

import org.bukkit.World;

/**
 * Created by Miles on 10/17/15.
 */
public class PhysicsObject {

    World world;

    //WORLD
    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

}

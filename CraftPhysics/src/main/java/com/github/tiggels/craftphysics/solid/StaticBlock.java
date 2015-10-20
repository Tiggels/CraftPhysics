package com.github.tiggels.craftphysics.solid;

import com.github.tiggels.craftphysics.annotations.method.OnTime;
import com.github.tiggels.craftphysics.annotations.type.Model;
import com.github.tiggels.craftphysics.annotations.type.Size;
import com.github.tiggels.craftphysics.physics.CraftRigidBody;
import org.bukkit.Location;
import org.bukkit.util.EulerAngle;

import javax.vecmath.Vector3f;

/**
 * Created by Miles on 10/20/15.
 */

@Size(0)
public class StaticBlock extends CraftRigidBody {

    public StaticBlock(Location location) throws Exception {
        super(0, location, new Vector3f(0,0,0), new EulerAngle(0,0,0), .3f, .5f, .5f, .7f);
    }

    @OnTime(22000)
    public void time() {

    }
}

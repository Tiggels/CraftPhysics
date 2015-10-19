package com.github.tiggels.craftphysics.util;

import javax.vecmath.Quat4f;

/**
 * Created by Miles on 10/19/15.
 */
public class ExtraQuatUtil {

    public float getPitch(Quat4f q) {
        return (float) Math.atan2(2.0 * (q.y * q.z + q.w * q.x), q.w * q.w - q.x * q.x - q.y * q.y + q.z * q.z);
    }


    public float getYaw(Quat4f q) {
        return (float) Math.asin(-2.0 * (q.x * q.z - q.w * q.y));
    }


    public float getRoll(Quat4f q) {
        return (float) Math.atan2(2.0 * (q.x * q.y + q.w * q.z), q.w * q.w + q.x * q.x - q.y * q.y - q.z * q.z);
    }
}

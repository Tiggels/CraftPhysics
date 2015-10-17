package com.github.tiggels.craftphysics.model;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.CompoundShape;
import com.bulletphysics.linearmath.QuaternionUtil;
import com.bulletphysics.linearmath.Transform;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.geom.Vec3d;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.io.File;

/**
 * Created by Miles on 10/17/15.
 */
public class ModelLoader {

    ObjectMapper maper;

    public ModelLoader() {
        maper = new ObjectMapper();
    }

    public Model loadJSON(String path) throws Exception {
        return maper.readValue(new File(path), Model.class);
    }

    public CollisionShape loadModel(String path) throws Exception {

        CompoundShape cs = new CompoundShape();
        Model model = maper.readValue(new File(path), Model.class);

        for (Element element : model.getElements()) {

            // This is the messy process of creating a new boxShape for every element

            float x = (element.getTo().get(0) - element.getFrom().get(0)) / 2;
            float y = (element.getTo().get(1) - element.getFrom().get(1)) / 2;
            float z = (element.getTo().get(2) - element.getFrom().get(2)) / 2;
            // This looks a bit odd don't it?
            // Well it is odd, minecraft JSON uses arrays for vec3, so we have to go though the array and normalise it
            // Also we divide by two because Jbullet uses half extents. "+b" is not the length, "+b to -b" is.

            BoxShape bs = new BoxShape(new Vector3f(x, y, z));

            Quat4f quaternion = new Quat4f();

            if (element.getRotation().getAxis().equals("x")) {
                QuaternionUtil.setEuler(quaternion, element.getRotation().getAngle(), 0, 0);

            } else if (element.getRotation().getAxis().equals("y")) {
                QuaternionUtil.setEuler(quaternion, 0, element.getRotation().getAngle(), 0);

            } else if (element.getRotation().getAxis().equals("z")) {
                QuaternionUtil.setEuler(quaternion, 0, 0, element.getRotation().getAngle());
            }

            Transform transform = new Transform(new Matrix4f(quaternion, new Vector3f(), 1));

            cs.addChildShape(transform, bs);
        }

        return cs;
    }
}

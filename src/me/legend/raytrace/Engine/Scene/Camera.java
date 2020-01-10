package me.legend.raytrace.Engine.Scene;

import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.normalize;

public class Camera {

    private Vec3 position, dir;
    private float fov;

    public Camera(Vec3 position, Vec3 dir, float fov){
        this.position = position;
        /* We're not going to be doing anything with the FOV / Dir for now lol */
    }

    public Ray getRay (float x, float y){
        return new Ray(this.position, new Vec3(x, y, 1F));
    }

}

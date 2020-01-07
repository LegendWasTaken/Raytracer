package me.legend.raytrace.Engine.Ray;

import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Ray {

    public Vec3 origin, dir;

    public Ray(Vec3 origin, Vec3 dir){
        this.origin = origin;
        this.dir = dir;
    }

    public Vec3 getPoint(float t){ return add(origin, scale(this.dir, t)); }

}

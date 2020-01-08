package me.legend.raytrace.Engine.Utils;

public class Vec3 {
    @Override public String toString(){ return "x: " + this.x + ", y: " + this.y + ", z: " + this.z; }
    public float x, y, z;
    public Vec3(){this(0);}
    public Vec3(float a){this(a, a, a);}
    public Vec3(float x, float y, float z){ this.x = x; this.y = y; this.z = z;}
}

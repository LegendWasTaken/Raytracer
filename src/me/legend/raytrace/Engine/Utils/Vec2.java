package me.legend.raytrace.Engine.Utils;

public class Vec2 {
    @Override public String toString(){ return "x: " + this.x + ", y: " + this.y; }
    public float x, y;
    public Vec2(){this(0F);}
    public Vec2(float a){this(a, a);}
    public Vec2(float x, float y){ this.x = x; this.y = y; }
}

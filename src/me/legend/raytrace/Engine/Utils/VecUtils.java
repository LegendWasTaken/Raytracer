package me.legend.raytrace.Engine.Utils;

public class VecUtils {
    public static float dot(Vec2 a, Vec2 b){ return (a.x * b.x + a.y * b.y); }
    public static float dist(Vec2 a, Vec2 b){ return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)); }
    public static float distSq(Vec2 a, Vec2 b){ return ((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)); }
    public static Vec2 add(Vec2 a, Vec2 b){ return new Vec2(a.x + b.x, a.y + b.y); }
    public static Vec2 sub(Vec2 a, Vec2 b){ return new Vec2(a.x - b.x, a.y - b.y); }
    public static Vec2 scale(Vec2 a, float scale){ return new Vec2(a.x * scale, a.y * scale); }
    public static Vec2 normalize(Vec2 a){ float t = a.x + a.y; return new Vec2(a.x / t, a.y / t); }
    public static float dot(Vec3 a, Vec3 b){ return (a.x * b.x + a.y * b.y + a.z * b.z); }
    public static float dist(Vec3 a, Vec3 b){ return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z)); }
    public static float distSq(Vec3 a, Vec3 b){ return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z); }
    public static Vec3 add(Vec3 a, Vec3 b){ return new Vec3(a.x + b.x, a.y + b.y, a.z + b.z); }
    public static Vec3 sub(Vec3 a, Vec3 b){ return new Vec3(a.x - b.x, a.y - b.y, a.z - b.z); }
    public static Vec3 scale(Vec3 a, float scale){ return new Vec3(a.x * scale, a.y * scale, a.z * scale); }
    public static Vec3 normalize(Vec3 a){ float t = a.x + a.y + a.z; return new Vec3(a.x / t, a.y / t, a.z / t); }
}

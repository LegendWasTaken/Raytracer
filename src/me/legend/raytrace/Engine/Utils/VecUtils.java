package me.legend.raytrace.Engine.Utils;

public class VecUtils {

    public static float dot(Vec2 a, Vec2 b){
        return (a.x * b.x + a.y * b.y);
    }

    public static float length(Vec2 a){
        return (float) Math.sqrt(a.x * a.x + a.y * a.y);
    }

    public static float dist(Vec2 a, Vec2 b){
        return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static float distSq(Vec2 a, Vec2 b){
        return ((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static Vec2 add(Vec2 a, Vec2 b){
        return new Vec2(a.x + b.x, a.y + b.y);
    }

    public static Vec2 sub(Vec2 a, Vec2 b){
        return new Vec2(a.x - b.x, a.y - b.y);
    }

    public static Vec2 abs(Vec2 a){
        return new Vec2(Math.abs(a.x), Math.abs(a.y));
    }

    public static Vec2 mod(Vec2 a, float mod){
        return new Vec2(a.x % mod, a.y % mod);
    }

    public static Vec2 floor(Vec2 a){
        return new Vec2((float) Math.floor(a.x),(float) Math.floor(a.y));
    }

    public static Vec2 scale(Vec2 a, float scale){
        return new Vec2(a.x * scale, a.y * scale);
    }

    public static Vec2 normalize(Vec2 a){
        float t = (float) Math.sqrt(a.x * a.x + a.y * a.y);
        return new Vec2(a.x / t, a.y / t);
    }

    public static float dot(Vec3 a, Vec3 b){
        return (a.x * b.x + a.y * b.y + a.z * b.z);
    }

    public static float length(Vec3 a){
        return (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
    }

    public static float dist(Vec3 a, Vec3 b){
        return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z));
    }

    public static float distSq(Vec3 a, Vec3 b){
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z);
    }

    public static Vec3 add(Vec3 a, Vec3 b){
        return new Vec3(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Vec3 sub(Vec3 a, Vec3 b){
        return new Vec3(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Vec3 cross(Vec3 a, Vec3 b){
        return new Vec3(
          a.y * b.z - a.z * b.y,
          a.z * b.x - a.x * b.z,
          a.x * b.y - a.y * b.x);
    }

    public static Vec3 abs(Vec3 a){
        return new Vec3(Math.abs(a.x), Math.abs(a.y), Math.abs(a.z));
    }

    public static Vec3 mod(Vec3 a, float scale){
        return new Vec3(a.x % scale, a.y % scale, a.z % scale);
    }

    public static Vec3 floor(Vec3 a){
        return new Vec3((float) Math.floor(a.x), (float) Math.floor(a.y), (float) Math.floor(a.z));
    }

    public static Vec3 scale(Vec3 a, float scale){
        return new Vec3(a.x * scale, a.y * scale, a.z * scale);
    }

    public static Vec3 normalize(Vec3 a){
        float t = (float) Math.sqrt(a.x * a.x + a.y * a.y + a.z * a.z);
        return new Vec3(a.x / t, a.y / t, a.z / t);
    }
}
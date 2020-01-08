package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Sphere implements Shape {

    private Vec3 origin;
    private TextureManager manager;
    private float radius, radius2;

    private Vec3 getNormal(Vec3 point){
        return normalize(sub(point, this.origin));
    }

    public Sphere(Vec3 origin, float radius, TextureManager manager){
        this.origin = origin;
        this.manager = manager;
        this.radius = radius;
        this.radius2 = radius * radius;
    }

    @Override public boolean loadTexture() {
        this.manager.loadTexture();
        switch(this.manager.getType()){
            case IMAGE: return this.manager.getColourSize() == 0;
            case SOLID: return this.manager.getColourSize() == 1;
            case CHECKERBOARD: return this.manager.getColourSize() == 2;
            default: return false;
        }
    }

    @Override public float hit(Ray ray) {
        Vec3 oc = sub(ray.origin, this.origin);
        float a = dot(ray.dir, ray.dir);
        float b = (float) (2.0 * dot(oc, ray.dir));
        float c = dot(oc, oc) - radius2;
        float disc = b*b - 4 * a * c;
        if(disc < 0) return -1F;
        float num = (float) (-b - Math.sqrt(disc));
        if(num > 0) return (float) (num / (2.0 * a));
        num = (float) (- b + Math.sqrt(disc));
        if(num > 0) return (float) (num / (2.0 * a));
        return -1F;
    }

    @Override public Colour getColourAt(Vec3 point) {
        switch(this.manager.getType()){
            case SOLID: return this.manager.getColour(0);
            case IMAGE:
                Vec3 n = normalize(sub(point, this.origin));
                float u = (float) (0.5 + Math.atan2(n.z, n.x));
                float v = (float) (0.5 - Math.asin(n.y) / Math.PI);
                return this.manager.getColourUV(u, v, false);
            case CHECKERBOARD:
                float scale = 2;
                float chess = (float) (Math.floor(point.x / scale) + Math.floor(point.y / scale) + Math.floor(point.z / scale));
                return this.manager.getColour(chess % 2 == 0F ? 0 : 1);
            default:
                return Colours.black.getColour();
        }
    }
}

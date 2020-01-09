package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Triangle implements Shape {

    private Vec3 a;
    private Vec3 b;
    private Vec3 c;
    private TextureManager manager;

    public Triangle(Vec3 a, Vec3 b, Vec3 c, TextureManager manager){
        this.a = a;
        this.b = b;
        this.c = c;

        this.manager = manager;
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

        Vec3 edge1 = sub(this.b, this.a);
        Vec3 edge2 = sub(this.c, this.a);
        Vec3 h, s, q;
        float a, f, u, v;

        h = cross(ray.dir, edge2);
        a = dot(edge1, h);
        if(a > -1E-7 && a < 1E-7) return -1F;

        f = 1.0F / a;
        s = sub(ray.origin, this.a);
        u = f * dot(s, h);
        if(u < 0.0F || u > 1.0F) return -1F;

        q = cross(edge1, s);
        v = f * dot(ray.dir, q);
        if(v < 0.0F || u + v > 1.0F) return -1F;

        float t = f * dot(edge2, q);
        if(t > 1E-7 && t < 1 - 1E-7) return t; else return -1F;
    }

    @Override
    public Colour getColourAt(Vec3 point) {
        switch(this.manager.getType()){
            case SOLID: return this.manager.getColour(0);
            case IMAGE: return this.manager.getColourUV(point.x, point.z, true);
            case CHECKERBOARD:
                float scale = 2;
                float chess = (float) (Math.floor(point.x / scale) + Math.floor(point.y / scale) + Math.floor(point.z / scale));
                return this.manager.getColour(chess % 2 == 0F ? 0 : 1);
            default:
                return Colours.black.getColour();
        }
    }
}

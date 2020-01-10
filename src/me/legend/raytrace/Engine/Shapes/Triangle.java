package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Triangle implements Shape {

    private Vec3 v0;
    private Vec3 v1;
    private Vec3 v2;
    private TextureManager manager;

    public Triangle(Vec3 v0, Vec3 v1, Vec3 v2, TextureManager manager){
        this.v0 = v0;
        this.v1 = v1;
        this.v2 = v2;

        this.manager = manager;
    }

    @Override
    public boolean loadTexture() {
        this.manager.loadTexture();
        switch(this.manager.getType()) {
            case IMAGE: return this.manager.getColourSize() == 0;
            case SOLID: return this.manager.getColourSize() == 1;
            case CHECKERBOARD: return this.manager.getColourSize() == 2;
            default: return false;
        }
    }

    @Override
    public float hit(Ray ray) {
        Vec3 e1, e2, h, s, q;
        float a, f, u, v;

        e1 = sub(v1, v0);
        e2 = sub(v2, v0);

        h = cross(ray.dir, e2);
        a = dot(v2, v0);
        if(a > -1E-6 && a < 1E-6) return -1F;

        f = 1.0F/a;
        s = sub(ray.origin, v0);
        u = f * (dot(s, h));
        if(u < 0.0 || u > 1.0) return -1F;

        q = cross(s, e1);
        v = f * dot(ray.dir, q);
        if(v < 0.0 || u + v > 1.0) return -1F;

        float t = f * dot(e2, q);
        if(t > 1E-6) return t; else return -1F;
    }

    @Override
    public Colour getColourAt(Vec3 point) {
        return Colours.aquamarine1.getColour();
    }
}

package me.legend.raytrace.Engine.Objects;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.ColourManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import java.util.ArrayList;
import java.util.List;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Plane implements Shape {

    private Vec3 origin, dir;
    private ColourManager manager;

    public Plane(Vec3 origin, Vec3 dir, ColourManager manager){
        this.origin = origin;
        this.dir = normalize(dir);
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
        float d = dot(ray.dir, this.dir);
        if(Math.abs(d) > 1E-6){
            float t = dot(sub(this.origin, ray.origin), this.dir) / d;
            return t > 0 ? t : -1F;
        }
        return -1F;
    }

    private Vec3 getNormal(){
        return this.dir;
    }

    @Override public Colour getColourAt(Vec3 point, List<Shape> shapes, List<Light> lights) {
        Colour colour;

        switch(this.manager.getType()){
            case SOLID: colour = this.manager.getColour(0); break;
            case IMAGE: colour = this.manager.getColourUV(point.x, point.z, true); break;
            case CHECKERBOARD:
                float scale = 4;
                float chess = (float) (Math.floor(point.x / scale) + Math.floor(point.y / scale) + Math.floor(point.z / scale));
                colour = this.manager.getColour(chess % 2 == 0F ? 0 : 1);
                break;
            default: colour = Colours.black.getColour(); break;
        }

        Vec3 delta = this.manager.getLight(add(point, scale(this.dir, 0.001F)), shapes, lights);
        return new Colour(colour.r * delta.x, colour.g * delta.y, colour.b * delta.z);
    }
}

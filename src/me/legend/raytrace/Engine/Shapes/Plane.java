package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Plane implements Shape {

    private Vec3 origin, dir;
    private TextureManager manager;

    public Plane(Vec3 origin, Vec3 dir, TextureManager manager){
        this.origin = origin;
        this.dir = dir.x > 1 || dir.y > 1 || dir.z > 1 ? normalize(dir) : dir;
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

    @Override public Colour getColourAt(Vec3 point) {
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

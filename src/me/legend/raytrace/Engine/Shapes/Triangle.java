package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Triangle implements Shape {

    private Vec3[] vertexs;
    private TextureManager manager;

    public Triangle(Vec3[] vertexs, TextureManager manager){
        this.vertexs = vertexs;
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

        Vec3 v0v1 = sub(this.vertexs[1], this.vertexs[0]);
        Vec3 v0v2 = sub(this.vertexs[2], this.vertexs[0]);
        Vec3 N = cross(v0v1, v0v2);
        float area2 = length(N);

        float ndotraydir = dot(N, ray.dir);
        if()

        return -1F;
    }

    @Override
    public Colour getColourAt(Vec3 point) {
        return Colours.white.getColour();
    }
}

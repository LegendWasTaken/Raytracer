package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Utils.Vec3;

public class Plane implements Shape {



    public Plane(Vec3 point, Vec3 dir){

    }


    @Override public boolean loadTexture() {
        return false;
    }

    @Override public float hit(Ray ray) {
        return 0;
    }

    @Override public Colour getColourAt(Vec3 point) {
        return null;
    }
}

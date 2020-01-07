package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Utils.Vec3;

public interface Shape {

    float hit(Ray ray);
    Colour getColourAt(Vec3 point);

}

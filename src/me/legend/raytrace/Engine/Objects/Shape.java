package me.legend.raytrace.Engine.Objects;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Utils.Vec3;

import java.util.List;

public interface Shape {
    boolean loadTexture();
    float hit(Ray ray);
    Colour getColourAt(Vec3 point, List<Shape> shapes, List<Light> lights);
}
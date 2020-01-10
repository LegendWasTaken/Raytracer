package me.legend.raytrace.Engine.Objects;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Utils.Vec3;

public class Light {

    private Vec3 point;
    private Colour colour;

    public Light(Vec3 point, Colour colour){
        this.point = point;
        this.colour = colour;
    }

    public Vec3 getPoint(){
        return this.point;
    }

    public Colour getColour(){
        return this.colour;
    }

}

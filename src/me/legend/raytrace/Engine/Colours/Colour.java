package me.legend.raytrace.Engine.Colours;

public class Colour {

    @Override
    public String toString(){
        return "r: " + this.r + ", g: " + this.g + ", b: " + this.b + " ]";
    }

    public Colour getNormalized(){ return new Colour(this.r / 255, this.g / 255, this.g / 255); }

    public float r, g, b;

    public Colour(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

}


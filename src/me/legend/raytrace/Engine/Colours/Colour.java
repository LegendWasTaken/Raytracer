package me.legend.raytrace.Engine.Colours;

public class Colour {

    @Override
    public String toString(){
        return "r: " + this.r + ", g: " + this.g + ", b: " + this.b + " ]";
    }

    public Colour getNormalized(){ return new Colour(this.r / 255, this.g / 255, this.g / 255); }

    public float r, g, b;

    public int IntRGB(){
        return (((int)this.r&0x0ff)<<16)|(((int)this.g&0x0ff)<<8)|((int)this.b&0x0ff);
    }

    public Colour(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

}


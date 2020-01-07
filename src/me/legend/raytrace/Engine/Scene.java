package me.legend.raytrace.Engine;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;
import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Shapes.Shape;
import me.legend.raytrace.Engine.Utils.Vec3;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Scene {

    private int x, y;
    private Vec3 cameraloc;
    private List<Shape> shapes;
    private Colour[][] pixels;

    public Scene(int x, int y){
        this.x = x;
        this.y = y;
        cameraloc = new Vec3(0, 0, -10);
        this.shapes = new ArrayList<>();
        this.pixels = new Colour[y][x];
    }

    public void render(){

    }

    public BufferedImage getImage(){
        return null;
    }

}

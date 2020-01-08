package me.legend.raytrace.Engine;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Shapes.Shape;
import me.legend.raytrace.Engine.Utils.Vec3;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.legend.raytrace.Engine.Utils.VecUtils.dist;
import static me.legend.raytrace.Engine.Utils.VecUtils.normalize;

public class Scene {

    private int x, y;
    private float xfix;
    private Vec3 cameraloc;
    private List<Shape> shapes;
    private Colour[][] pixels;

    public Scene(int x, int y){
        this.x = x;
        this.y = y;
        this.xfix = (float) this.x / (float) this.y;
        cameraloc = new Vec3(0, 0, -10);
        this.shapes = new ArrayList<>();
        this.pixels = new Colour[y][x];
        for(int i=0; i<this.y; i++) for(int j=0; j<this.x; j++) this.pixels[i][j] = Colours.black.getColour();
    }

    public void addShape(Shape shape){ this.shapes.add(shape); }

    private boolean loadTextures(){
        for(int i=0; i<this.shapes.size(); i++){
            if(!this.shapes.get(i).loadTexture()) return false;
        }
        return true;
    }

    public void render(){
        if(!this.loadTextures()){
            throw new RuntimeException("Error loading textures");
        }
        //float[][] distances = new float[this.y][this.x];
        //float maxDistance = 0;
        long startTime = System.currentTimeMillis();
        System.out.println("Starting the render at " + startTime);
        for(int i=0; i<this.y; i++){
            for(int j=0; j<this.x; j++){
                Ray ray = new Ray(this.cameraloc,
                  new Vec3(((float) j / this.y * 2) - this.xfix, ((float) i / this.y * 2) - 1, 1F)
                );

                float bestDistance = Float.POSITIVE_INFINITY;
                Shape bestShape = null;
                for(int a=0; a<this.shapes.size(); a++){
                    Shape cur = this.shapes.get(a);
                    float dist = cur.hit(ray);
                    if(dist > 0 && dist < bestDistance){
                        bestDistance = dist;
                        bestShape = cur;
                    }
                }
                if(bestShape != null) this.pixels[i][j] = bestShape.getColourAt(ray.getPoint(bestDistance));
            }
        }

        long delta = System.currentTimeMillis() - startTime;
        System.out.println("Finished rendering, took " + (int) (delta / 1000) + " seconds, and " + delta + "ms");

        /*
        for(int i=0; i<this.y; i++){
            for(int j=0; j<this.x; j++){
                Vec3 colour = new Vec3(distances[i][j] / maxDistance);
                this.pixels[i][j] = new Colour(colour.x * 255, colour.y * 255, colour.z * 255);
            }
        }*/
    }

    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(this.x, this.y, 1);
        for(int i=0; i<this.y; i++) for(int j=0; j<this.x; j++) image.setRGB(j, i, this.pixels[i][j].IntRGB());
        return image;
    }

}

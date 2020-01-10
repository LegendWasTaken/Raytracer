package me.legend.raytrace.Engine;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Objects.Light;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Objects.Shape;
import me.legend.raytrace.Engine.Utils.Vec3;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static me.legend.raytrace.Engine.Utils.VecUtils.dist;
import static me.legend.raytrace.Engine.Utils.VecUtils.normalize;

public class Scene {

    private int x, y, aa, aasize;
    private float xfix;
    private Vec3 cameraloc;
    private List<Shape> shapes;
    private List<Light> lights;
    private Colour background;
    private Colour[][] pixels;

    public Scene(int x, int y, int aa, Colour background){
        this.x = x;
        this.y = y;
        this.aa = aa; // No clue if this is how anti aliasing is done
        this.aasize = aa * aa;
        this.xfix = (float) this.x / (float) this.y;
        cameraloc = new Vec3(0, 0, -10);
        this.shapes = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.background = background;
        this.pixels = new Colour[y][x];
        for(int i=0; i<this.y; i++) for(int j=0; j<this.x; j++) this.pixels[i][j] = this.background;
    }

    public void addShape(Shape shape){ this.shapes.add(shape); }

    public void addLight(Light light){ this.lights.add(light); }

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
        long startTime = System.currentTimeMillis();
        System.out.println("Starting to render");
        for(int i=0; i<this.y; i++){
            for(int j=0; j<this.x; j++){

                Colour[] aacolours = new Colour[aa*aa]; /* Array because performance */

                // Anti aliasing time pog
                for(int ax = 0; ax <aa; ax++){
                    for(int ay = 0; ay<aa; ay++){
                        Ray ray = new Ray(this.cameraloc,
                          normalize(new Vec3((((j + ((float) ax / (float) aa))) / this.y * 2) - this.xfix, (((i + ((float) ay / (float) aa))) / this.y * 2) - 1, 1F))
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
                        aacolours[ax + ay] = bestShape != null ? bestShape.getColourAt(ray.getPoint(bestDistance), this.shapes, this.lights) : this.background;
                    }
                }

                Vec3 fc = new Vec3();

                float size = 0;

                for(int a=0; a<aasize; a++){
                    if(aacolours[a] != null){
                        size++;
                        fc.x += aacolours[a].r;
                        fc.y += aacolours[a].g;
                        fc.z += aacolours[a].b;
                    }
                }

                this.pixels[i][j] = new Colour(fc.x / size, fc.y / size, fc.z / size);

            }
        }

        long delta = System.currentTimeMillis() - startTime;
        /* Need to fix the finished rendering message. will do later...*/
        System.out.println("Finished rendering, took " + (int) (delta / 1000) + " seconds, and " + delta + "ms");
    }

    public BufferedImage getImage(){
        BufferedImage image = new BufferedImage(this.x, this.y, 1);
        for(int i=0; i<this.y; i++) for(int j=0; j<this.x; j++) image.setRGB(j, i, this.pixels[i][j].IntRGB());
        return image;
    }

}

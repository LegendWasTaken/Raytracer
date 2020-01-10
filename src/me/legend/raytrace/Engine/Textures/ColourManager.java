package me.legend.raytrace.Engine.Textures;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Objects.Light;
import me.legend.raytrace.Engine.Objects.Shape;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Utils.Vec3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

@SuppressWarnings("all")
public class ColourManager {

    private TextureType type;
    private List<Colour> colours;
    private Colour[][] pixels;
    private Map<Integer, Colour[][]> mipmaplevels;
    private String texturepath = null;
    private static float defaultLightLevel = 0.0F;
    private int height, width, dx, dy;

    public ColourManager(TextureType type){
        this.type = type;
        this.colours = new ArrayList<>();
    }

    public ColourManager(TextureType type, String texturepath){
        this(type, texturepath, 0, 0);
    }

    public ColourManager(TextureType type, String texturepath, int dx){
        this(type, texturepath, dx, 0);
    }

    public ColourManager(TextureType type, String texturepath, int dx, int dy){
        this.type = type;
        this.mipmaplevels = new HashMap<>(); // To be implemented soonTM
        this.colours = new ArrayList<>();
        this.texturepath = texturepath;
        this.dx = dx;
        this.dy = dy;
    }

    /* Handling texture stuff */
    public TextureType getType() { return this.type; }

    public void setDefaultLightLevel(float defaultLightLevel){
        this.defaultLightLevel = defaultLightLevel;
    }

    public void loadTexture(){
        if(this.texturepath != null){
            try {
                /* Changing how images are handled and cached (for the better) :p */
                BufferedImage img = ImageIO.read(new File(this.texturepath));

                this.height = img.getHeight();
                this.width = img.getWidth();
                this.pixels = new Colour[this.height][this.width];
                for(int i=0; i<this.height; i++) for(int j=0; j<this.width; j++) {
                    int intrgb = img.getRGB(j, i);
                    this.pixels[this.height - i - 1][j] = new Colour(intrgb >> 16 & 0xFF, (intrgb >> 8) & 0xFF, intrgb & 0xFF);
                }
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public Colour getColourUV(float x, float y, boolean infinite) {
        if(infinite){
            // This is just so that the image gets "flipped" when it's on negative coordinates (to make it not mirror)
            if(x < 0) x += this.width;
            if(y < 0) y += this.height;
        }

        /* Moved a lot of calculations into the loadtexture function. Instead of iterating through the entire image int values every time we want to get a colour
        * we can do a direct look up since it's stored in an array now. Should be a shit ton faster than before (might benchmark dunno)*/
        x = (((Math.abs(x) * this.width) + this.dx) % this.width);
        y = (((Math.abs(y) * this.height) + this.dy) % this.height);

        // Bilinear colour look up (this is going to look buttterrrrr smooooth)
        int i = ((int) x);
        int j = ((int) y);

        float sPram = x - i;
        float tPram = y - j;

        // tex colours yeet
        Vec3 c0 = this.pixels[j][i].toVec();
        Vec3 c1 = this.pixels[j][(i + 1) % this.width].toVec();
        Vec3 c2 = this.pixels[(j + 1) % this.height][i].toVec();
        Vec3 c3 = this.pixels[(j + 1) % this.height][(i + 1) % this.width].toVec();

        Vec3 edge0 = add(scale(sub(c1, c0), tPram), c0);
        Vec3 edge1 = add(scale(sub(c3, c2), tPram), c2);

        Vec3 computed = add(scale(sub(edge1, edge0), sPram), edge1);
        return new Colour(computed.x, computed.y, computed.z);
    }

    /* Handling shadow stuff and yeah lol */
    public float getLightIntensity(Vec3 point, List<Shape> shapes, List<Light> lights){
        float intensity = 0.0F;
        for(int i=0; i<lights.size(); i++){
            Ray ray = new Ray(point, normalize(sub(lights.get(i).getPoint(), point)));
            boolean hit = false;
            for(int j=0; j<shapes.size(); j++){
                if(shapes.get(i).hit(ray) > 0.0F) {
                    hit = true;
                    break;
                }
            }
            if(!hit){
                intensity += 0.2F;
            } else {
                intensity /= 0.5F;
            }
        }
        return intensity;
    }

    /* Handling colour stuff */
    public void addColour(Colour in){
        this.colours.add(in);
    }

    public void addColour(Colour[] colours){
        this.colours.addAll(Arrays.asList(colours));
    }

    public void addColour(List<Colour> colours){
        this.colours.addAll(colours);
    }

    public int getColourSize(){
        return this.colours.size();
    }

    public Colour getColour(int index){
        return this.colours.get(index);
    }

}

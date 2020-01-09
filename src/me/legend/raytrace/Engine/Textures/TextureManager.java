package me.legend.raytrace.Engine.Textures;

import me.legend.raytrace.Engine.Colours.Colour;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextureManager {

    private TextureType type;
    private List<Colour> colours;
    private Colour[][] pixels;
    private String texturepath = null;
    private int height, width, dx, dy;

    public TextureManager(TextureType type){
        this.type = type;
        this.colours = new ArrayList<>();
    }

    public TextureManager(TextureType type, String texturepath){
        this(type, texturepath, 0, 0);
    }

    public TextureManager(TextureType type, String texturepath, int dx){
        this(type, texturepath, dx, 0);
    }

    public TextureManager(TextureType type, String texturepath, int dx, int dy){
        this.type = type;
        this.colours = new ArrayList<>();
        this.texturepath = texturepath;
        this.dx = dx;
        this.dy = dy;
    }

    /* Handling texture stuff */
    public TextureType getType() { return this.type; }

    public void loadTexture(){
        if(this.texturepath != null){
            try {
                /* Changing how images are handled and cached (for the better) :p */
                BufferedImage img = ImageIO.read(new File(this.texturepath));

                this.width = img.getWidth();
                this.height = img.getHeight();
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
            // This occurs when the shape is infinite, and we need to calculate the UV coordinates in this class, since we have access to the image size
            if(x < 0) x += this.width;
            if(y < 0) y += this.height;
        }

        /* Fixed the bug, it was trying to flip the image while rendering, going to be moving this into the loadtexture function, to hopefully increase speed by a decent amount aswell*/
        int u = (int) (((Math.abs(x) * this.width) + this.dx) % this.width);
        int v = (int) (((Math.abs(y) * this.height) + this.dy) % this.height);

        // Bilinear colour look up (this is going to look buttterrrrr smooooth)

        return this.pixels[v][u];
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

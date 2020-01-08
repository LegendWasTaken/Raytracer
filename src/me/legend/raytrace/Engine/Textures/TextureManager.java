package me.legend.raytrace.Engine.Textures;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Utils.Vec2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.legend.raytrace.Engine.Utils.NumberUtils.clamp;

public class TextureManager {

    private TextureType type;
    private List<Colour> colours;
    private BufferedImage texture;
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
                File image = new File(this.texturepath);
                this.texture = ImageIO.read(image);
                this.width = this.texture.getWidth();
                this.height = this.texture.getHeight();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public Colour getColourUV(float x, float y) {
        int u = (int) (((Math.abs(x) * this.width) + this.dx)% this.width + 1);
        int v = (int) (((Math.abs(y) * this.height) + this.dy) % this.height + 1);
        int intrgb = this.texture.getRGB(this.width - u , this.height - v);
        return new Colour(intrgb >> 16 & 0xFF, (intrgb >> 8) & 0xFF, intrgb & 0xFF);

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

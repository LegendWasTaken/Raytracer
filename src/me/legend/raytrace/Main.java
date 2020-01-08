package me.legend.raytrace;

import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Scene;
import me.legend.raytrace.Engine.Shapes.Plane;
import me.legend.raytrace.Engine.Shapes.Sphere;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Textures.TextureType;
import me.legend.raytrace.Engine.Utils.Vec3;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String... args){
        Scene scene = new Scene(1280, 720);

        TextureManager texture = new TextureManager(TextureType.IMAGE, "./Textures/ahyes.png");
        TextureManager sphere = new TextureManager(TextureType.IMAGE, "./Textures/trumplol.png", 300, 120);
//        sphere.addColour(Colours.cadetblue.getColour());
//        sphere.addColour(Colours.warmgrey.getColour() );
        scene.addShape(new Sphere(new Vec3(0, -3, 10), 9F, sphere));
        scene.addShape(new Plane(new Vec3(0, 6, 0), new Vec3(0, 1, 0), texture));
        scene.render();
        int fileNumber = 1;
        File image;
        while(true){
            image = new File("./renders/render_" + fileNumber + ".png");
            if(!image.exists()) break; else fileNumber++;
        }
        try {
            ImageIO.write(scene.getImage(), "PNG", image);
        } catch (IOException ex){ ex.printStackTrace();}
    }

}

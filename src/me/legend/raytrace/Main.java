package me.legend.raytrace;

import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Scene;
import me.legend.raytrace.Engine.Shapes.Plane;
import me.legend.raytrace.Engine.Shapes.Sphere;
import me.legend.raytrace.Engine.Shapes.Triangle;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Textures.TextureType;
import me.legend.raytrace.Engine.Utils.Vec3;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String... args){


        /* (width, height, rays/pixel/axis, background colour) */
        Scene scene = new Scene(1280, 720, 4, Colours.lightgrey.getColour());

        TextureManager floor = new TextureManager(TextureType.IMAGE, "Textures/grass.jpg");
//        TextureManager sphere = new TextureManager(TextureType.CHECKERBOARD);
//        sphere.addColour(Colours.tan.getColour());
//        sphere.addColour(Colours.darkgreen.getColour() );

//        floor.addColour(Colours.black.getColour());
//        floor.addColour(Colours.ghostwhite.getColour());

//        scene.addShape(new Sphere(new Vec3(0, -2, 20), 8F, sphere));
        scene.addShape(new Plane(new Vec3(0, 8, 0), new Vec3(0, 1, 0), floor));
//        scene.addShape(new Triangle(new Vec3(5, 0, 0), new Vec3(0, 5, 0), new Vec3(-5, 0, 0), floor));
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

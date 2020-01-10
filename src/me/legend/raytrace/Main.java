package me.legend.raytrace;

import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Objects.Light;
import me.legend.raytrace.Engine.Scene.Camera;
import me.legend.raytrace.Engine.Scene.Scene;
import me.legend.raytrace.Engine.Objects.Plane;
import me.legend.raytrace.Engine.Objects.Sphere;
import me.legend.raytrace.Engine.Textures.ColourManager;
import me.legend.raytrace.Engine.Textures.TextureType;
import me.legend.raytrace.Engine.Utils.Vec3;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String... args){


        /* (width, height, rays/pixel/axis, background colour) */
        Camera camera = new Camera(new Vec3(0, 0, -10), new Vec3(0, 0, 0), 1F);

        Scene scene = new Scene(camera, 1280, 720, 4, Colours.lightgrey.getColour());

        ColourManager.setDefaultLightLevel(1.0F);

        ColourManager light = new ColourManager(TextureType.SOLID);
        ColourManager floor = new ColourManager(TextureType.IMAGE, "./Textures/grass.jpg");
        ColourManager sphere = new ColourManager(TextureType.CHECKERBOARD);
        light.addColour(Colours.lightgoldenrodyellow.getColour());
        sphere.addColour(Colours.white.getColour());
        sphere.addColour(Colours.ivoryblack.getColour());

//        floor.addColour(Colours.palevioletred.getColour());

        scene.addLight(new Light(new Vec3(10, -4, 26), Colours.indianred1.getColour()));
        scene.addLight(new Light(new Vec3(-10, 0, 4), Colours.powderblue.getColour()));
//        scene.addShape(new Sphere(new Vec3(10, -4, 26), 3F, light));
        scene.addShape(new Sphere(new Vec3  (-12, 0, 16), 5F, sphere));
        scene.addShape(new Sphere(new Vec3(12, 2, 20), 3F, sphere));
        scene.addShape(new Plane(new Vec3(0, 8, 0), new Vec3(0, -1, 0), floor));
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

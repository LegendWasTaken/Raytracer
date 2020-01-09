package me.legend.raytrace.Engine.Shapes;

import me.legend.raytrace.Engine.Colours.Colour;
import me.legend.raytrace.Engine.Colours.Colours;
import me.legend.raytrace.Engine.Ray.Ray;
import me.legend.raytrace.Engine.Textures.TextureManager;
import me.legend.raytrace.Engine.Utils.Vec3;

import static me.legend.raytrace.Engine.Utils.VecUtils.*;

public class Triangle implements Shape {

    private Vec3 a;
    private Vec3 b;
    private Vec3 c;
    private Vec3 dir;
    private TextureManager manager;

    public Triangle(Vec3 a, Vec3 b, Vec3 c, TextureManager manager){
        this.a = a;
        this.b = b;
        this.c = c;

        this.manager = manager;

        Vec3 ca = sub(c, a);
        Vec3 ba = sub(b, a);
        this.dir = normalize(cross(ca, ba));
    }



    @Override public boolean loadTexture() {
        this.manager.loadTexture();
        switch(this.manager.getType()){
            case IMAGE: return this.manager.getColourSize() == 0;
            case SOLID: return this.manager.getColourSize() == 1;
            case CHECKERBOARD: return this.manager.getColourSize() == 2;
            default: return false;
        }
    }

    @Override public float hit(Ray ray) {
        float d = dot(ray.dir, this.dir);

        if(Math.abs(d) > 1E-6){
            float b = dot(sub(this.a, ray.origin), this.dir) / d;

            return b > 0 ? b : -1F;

            /*
            float qx = scale(ray.dir, dist2plane).x + ray.origin.x;
            float qy = scale(ray.dir, dist2plane).y + ray.origin.y;
            float qz = scale(ray.dir, dist2plane).z + ray.origin.z;
            Vec3 q = new Vec3(qx, qy, qz);

            Vec3 ca = new Vec3(this.c.x - this.a.x, this.c.y - this.a.y, this.c.z - this.a.z);
            Vec3 qa = new Vec3(q.x - this.a.x, q.y - this.a.y, q.z - this.a.z);
            float test1 = dot(cross(ca, qa), this.dir);

            Vec3 bc = new Vec3(this.b.x - this.c.x, this.b.y - this.c.y, this.b.z - this.c.z);
            Vec3 qc = new Vec3(q.x - this.c.x, q.y - this.c.y, q.z - this.c.z);
            float test2 = dot(cross(bc, qc), this.dir);

            Vec3 ab = new Vec3(this.a.x - this.b.x, this.a.y - this.b.y, this.a.z - this.b.z);
            Vec3 qb = new Vec3(q.x - this.b.x, q.y - this.b.y, q.z - this.b.z);
            float test3 = dot(cross(ab, qb), this.dir);

            if((test1 >= 0) && (test2 >= 0) && (test3 >= 0)){
                return dist2plane;
            }*/

        }
        return -1F;
    }

    @Override
    public Colour getColourAt(Vec3 point) {
        switch(this.manager.getType()){
            case SOLID: return this.manager.getColour(0);
            case IMAGE: return this.manager.getColourUV(point.x, point.z, true);
            case CHECKERBOARD:
                float scale = 2;
                float chess = (float) (Math.floor(point.x / scale) + Math.floor(point.y / scale) + Math.floor(point.z / scale));
                return this.manager.getColour(chess % 2 == 0F ? 0 : 1);
            default:
                return Colours.black.getColour();
        }
    }
}

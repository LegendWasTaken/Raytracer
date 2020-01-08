package me.legend.raytrace.Engine.Utils;

public class NumberUtils {
    public static int clamp(int a, int b, int c){ return a < b ? b : a > c ? c : a; }

}

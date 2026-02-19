package com.academy.four.util;

import java.awt.*;

public class ColorUtil {
    //java.awt.Color -> String kojeg ćemo snimit u bazi
    public static String colorToString(Color color) {
        return "#%02X%02X%02X".formatted(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Color stringToColor(String color) {
        if(color == null || color.isEmpty()) return null;
        if(color.startsWith("#")) {
            color = color.substring(1);
        }
        int red = Integer.parseInt(color.substring(0, 2), 16);
        int green = Integer.parseInt(color.substring(2, 4), 16);
        int blue = Integer.parseInt(color.substring(4, 6), 16);
        return new Color(red, green, blue);
    }
}

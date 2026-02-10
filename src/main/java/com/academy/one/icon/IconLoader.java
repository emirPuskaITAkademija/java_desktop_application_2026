package com.academy.one.icon;

import javax.swing.*;
import java.net.URL;
//1. iz kojeg konteksta učitavamo ikonice : ButtonPanel.class
public class IconLoader {

    private final Class<?> clazz;

    public IconLoader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Icon loadIcon(String pathToIcon) {
        //ClassLoader je Java mehanizam koji:
        //1. učitava klase
        //2. ali učitava i resurse koje klasa koristi iz projekta(slike, fajlove, .png, .gif..)
        ClassLoader classLoader = clazz.getClassLoader();
        URL url = classLoader.getResource(pathToIcon);
        if (url == null) {
            System.err.println("Icon resource not found: " + pathToIcon);
            return null;
        }
        return new ImageIcon(url);
    }
}

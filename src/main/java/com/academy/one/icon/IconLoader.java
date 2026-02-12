package com.academy.one.icon;

import javax.swing.*;
import java.net.URL;
//1. iz kojeg konteksta učitavamo ikonice : ButtonPanel.class
/**
 * IconLoader
 * src/main/java/com/academy/one/ButtonPanel.java -> IconLoader
 * <p>
 * IconLoader => on učitava iz tog konteksta u kojem se nalazi ButtonPanel.java eskterni fajl left.gif
 * <p>
 * Mora učitati kao ImageIcon/Icon => eksterni fajl relativno u odnosu ButtonPanel.class objekat src/main/resources/left.gif
 * <p>
 * IconLoader iconLoader = new IconLoader(ButtonPanel.class);
 * TARGET : src/main/java/com/academy/one/ButtonPanel.class
 * <p>
 * C:\Projects\java_desktop_application_2026\src\main\resources\one\left.gif
 * ---->  ../../../../../resources/left.gif  => URL url = classLoader.getResource(one/left.gif);
 * ---> ImageIcon
 *
 */

public class IconLoader {

    private final Class<?> clazz;

    public IconLoader(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Icon loadIcon(String relativePath) {
        //ClassLoader je Java mehanizam koji:
        //1. pronalazi byte kod klase -> učita u JVM -> pretvara u CLass objekat -> omogući da se klasa koristi u aplikaciji
        //2. ali učitava i resurse koje klasa koristi iz projekta(slike, fajlove, .png, .gif..)
        /**
         * Tri osnovna class loader postoje u JAcvi
         * <li>1. Bootstrap ClassLoader - učita osnovne java.lang.... String, Object, System...dio JVM-a</li>
         * <li>2. Platform Extension ClassLoader -> javax.swing.JFrame, neke JDK biblioteke</li>
         * <li>3. Application ClassLoader -> target/classes , .jar fajlove </li>
         *
         * parent delegation model
         */
        ClassLoader classLoader = clazz.getClassLoader();
        URL url = classLoader.getResource(relativePath);
        if (url == null) {
            System.err.println("Icon resource not found: " + relativePath);
            return null;
        }
        return new ImageIcon(url);
    }
}

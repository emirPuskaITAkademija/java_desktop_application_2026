package com.academy.one.icon;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
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
    //ClassLoader je Java mehanizam koji:
    //1. pronalazi byte kod klase -> učita u JVM -> pretvara u CLass objekat -> omogući da se klasa koristi u aplikaciji
    //2. ali učitava i resurse koje klasa koristi iz projekta(slike, fajlove, .png, .gif..)

    /**
     * Tri osnovna class loader postoje u JAcvi
     * <li>1. Bootstrap ClassLoader - učita osnovne java.lang.... String, Object, System...dio JVM-a</li>
     * <li>2. Platform Extension ClassLoader -> javax.swing.JFrame, neke JDK biblioteke</li>
     * <li>3. Application ClassLoader -> target/classes , .jar fajlove </li>
     * <p>
     * parent delegation model
     */
    public Icon loadIcon(String relativePath) {
        return loadIcon(relativePath, 18, 18);
    }

    public Icon loadIcon(String relativePath, int width, int height) {
        ClassLoader classLoader = clazz.getClassLoader();
        URL url = classLoader.getResource(relativePath);
        if (url == null) {
            System.err.println("Icon resource not found: " + relativePath);
            return null;
        }
        String relativePathLowerCase = relativePath.toLowerCase();
        try {
            if (relativePathLowerCase.endsWith(".svg")) {
                return loadSvgAsIcon(url, width, height);
            }
            ImageIcon imageIcon = new ImageIcon(url);
            if (width > 0 && height > 0) {
                Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }
            return imageIcon;
        } catch (Exception e) {
            System.err.println("Icon resource not found: " + relativePath);
            return null;
        }
    }

    //SVG(XML definirana slika) <----> Swing Icon(BuufferedImage)
    private Icon loadSvgAsIcon(URL svgUrl, int width, int height) throws Exception {
        try (InputStream inputStream = svgUrl.openStream()) {
            BufferedImage image = transcodeSvgToBufferedImage(inputStream, width, height);
            return new ImageIcon(image);
        }
    }

    private BufferedImage transcodeSvgToBufferedImage(InputStream inputStream, int width, int height) throws TranscoderException {
        //svg xml sadržaj kao input stream
        TranscoderInput transcoderInput = new TranscoderInput(inputStream);
        BufferedImageTranscoder bufferedImageTranscoder = new BufferedImageTranscoder();
        if (width > 0) {
            bufferedImageTranscoder.addTranscodingHint(ImageTranscoder.KEY_WIDTH, (float) width);
        }
        if (height > 0) {
            bufferedImageTranscoder.addTranscodingHint(ImageTranscoder.KEY_HEIGHT, (float) height);
        }
        //svg->wrapovan kao TranscoderINput
        bufferedImageTranscoder.transcode(transcoderInput, null);
        BufferedImage bufferedImage = bufferedImageTranscoder.getBufferedImage();
        if (bufferedImage == null) {
            throw new TranscoderException("Transcoding failed");
        }
        return bufferedImage;
    }

    private static final class BufferedImageTranscoder extends ImageTranscoder {
        private BufferedImage image;

        @Override
        public BufferedImage createImage(int width, int height) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        }

        @Override
        public void writeImage(BufferedImage bufferedImage, TranscoderOutput transcoderOutput) throws TranscoderException {
            this.image = bufferedImage;
        }

        public BufferedImage getBufferedImage() {
            return image;
        }
    }
}

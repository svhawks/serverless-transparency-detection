package com.mojilala.transparency_detection;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by mrsfy on 15-Feb-17.
 * TransparencyAnalyzer class analyzes the background transparency of an image.
 */
public class TransparencyAnalyzer {

    public double transparencyRate(BufferedImage img) {
        int transparentPixels = 0;

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                if ((pixel >> 24) == 0x00) {
                    transparentPixels++;
                }
            }
        }

        return ((double) transparentPixels) / (img.getHeight()*img.getWidth());
    }

    public boolean isTransparent( BufferedImage img ) {

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                if ((pixel >> 24) == 0x00) {
                    return true;
                }
            }
        }

        return false;
    }

    public DetectionResult analyze(String url) throws IOException {

        System.setProperty("http.agent", "Chrome");
        BufferedImage img = ImageIO.read(new URL(url).openStream());
        boolean isTransparent = isTransparent(img);

        return new DetectionResult(isTransparent);
    }

}
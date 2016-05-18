/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafikak2;

import java.util.ArrayList;

public class ImageTransform {

    PpmPanel imagePanel;

    public ImageTransform(PpmPanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    public void transformImage(int appliedRed, int appliedGreen, int appliedBlue, ArrayList<ArrayList> operationLUTList) {

        int width = imagePanel.getImageWidth();
        int heigth = imagePanel.getImageHeight();
        int[][] pixels = imagePanel.getPixels();
        int[][] transformedPixels = new int[heigth][width];

        for (int r = 0; r < heigth; r++) {
            for (int c = 0; c < width; c++) {
                int oryginalRed = (pixels[r][c] >> 16) & 0xFF;
                int oryginalGreen = (pixels[r][c] >> 8) & 0xFF;
                int oryginalBlue = (pixels[r][c]) & 0xFF;
                ArrayList<Integer> redInnerList = operationLUTList.get(oryginalRed);
                ArrayList<Integer> greenInnerList = operationLUTList.get(oryginalGreen);
                ArrayList<Integer> blueInnerList = operationLUTList.get(oryginalBlue);
                int newRed = redInnerList.get(appliedRed);
                int newGreen = greenInnerList.get(appliedGreen);
                int newBlue = blueInnerList.get(appliedBlue);

                int rgb = 65536 * newRed + 256 * newGreen + newBlue;
                transformedPixels[r][c] = rgb;
            }
        }

        System.out.println("Transformation add done. Displaying transformed image.");
        imagePanel.setPixels(transformedPixels);
        imagePanel.displayImage();
    }

    public void imageToDarkScale() {
        int width = imagePanel.getImageWidth();
        int heigth = imagePanel.getImageHeight();
        int[][] pixels = imagePanel.getPixels();
        int[][] darkScalePixels = new int[heigth][width];

        for (int r = 0; r < heigth; r++) {
            for (int c = 0; c < width; c++) {
                int oryginalRed = (pixels[r][c] >> 16) & 0xFF;
                int oryginalGreen = (pixels[r][c] >> 8) & 0xFF;
                int oryginalBlue = (pixels[r][c]) & 0xFF;

                int darkScaleCurrPixel = (oryginalRed + oryginalGreen + oryginalBlue) / 3;
                int newRed = darkScaleCurrPixel;
                int newGreen = darkScaleCurrPixel;
                int newBlue = darkScaleCurrPixel;

                int rgb = 65536 * newRed + 256 * newGreen + newBlue;
                darkScalePixels[r][c] = rgb;

            }
        }
        imagePanel.setPixels(darkScalePixels);
        imagePanel.displayImage();
        System.out.println("Darkscale done!");
    }
    
    public void imageToDarkScale2() {
        int width = imagePanel.getImageWidth();
        int heigth = imagePanel.getImageHeight();
        int[][] pixels = imagePanel.getPixels();
        int[][] darkScalePixels = new int[heigth][width];

        for (int r = 0; r < heigth; r++) {
            for (int c = 0; c < width; c++) {
                int oryginalRed = (pixels[r][c] >> 16) & 0xFF;
                int oryginalGreen = (pixels[r][c] >> 8) & 0xFF;
                int oryginalBlue = (pixels[r][c]) & 0xFF;

                int darkScaleCurrPixel = (int) (oryginalRed*0.299) +(int) (oryginalGreen*0.587) +(int) (oryginalBlue*0.114);
                int newRed = darkScaleCurrPixel;
                int newGreen = darkScaleCurrPixel;
                int newBlue = darkScaleCurrPixel;

                int rgb = 65536 * newRed + 256 * newGreen + newBlue;
                darkScalePixels[r][c] = rgb;

            }
        }
        imagePanel.setPixels(darkScalePixels);
        imagePanel.displayImage();
        System.out.println("Darkscale done!");
    }
}

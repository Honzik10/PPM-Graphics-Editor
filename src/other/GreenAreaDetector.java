/*
    Green:
    125-255-0 Spring green
    0-255-0 Green
    0-255-125 Turqoise
    
 */
package other;

import binaryzation.Binaryzation;
import java.awt.Color;
import mainpckg.MainFrame;
import morfology.Morfology;
import morfology.StructElement;

public class GreenAreaDetector {

    private float minH;
    private float maxH;
    private float minS;
    private float maxS;
    private float minB;
    private float maxB;
    PpmPanel imgPanel;
    MainFrame mainFrame;
    int blackRGB = 0;

    public GreenAreaDetector(PpmPanel imgPanel, MainFrame mainFrame) {
        minH = (float) 74 / 360;
        maxH = (float) 155 / 360;
        minS = 0.49f;
        minB = 0.4f;
        this.imgPanel = imgPanel;
        this.mainFrame = mainFrame;
    }

    public double detect(int[][] pixels) {
        int checkedAreaPixelNumber = calculateCheckedAreaSize(pixels);
        
        //Removing other than green pixels
        int[][] newPixels = removeOtherPixelsThanGreen(pixels);
        
        //Binaryzation with 38% of black
        Binaryzation binaryzation = new Binaryzation(imgPanel, mainFrame);
        int binaryzationThreshold = binaryzation.calculateBlackPercentageThreshold(38);
        binaryzation.binaryze(newPixels, binaryzationThreshold);
        
        imgPanel.setPixels(newPixels);
        imgPanel.displayImage();
        
        Morfology morfology = new Morfology(imgPanel);
        int[] elements = new int[]{0, 0, 0, 0, -1, -1, 0, 0, -1};
        StructElement structEl = new StructElement(3, 3, 1, 1, elements);

        int[][] pixelsCopy = MyArray.copyArray(imgPanel.getPixels());
        for (int i = 0; i < 3; i++) {
            newPixels = morfology.dilation(newPixels, pixelsCopy, structEl);
            pixelsCopy = MyArray.copyArray(newPixels);
        }
        imgPanel.setPixels(pixelsCopy);
        imgPanel.displayImage();
        
        double percentOfGreen = calculateNumberOfBlackPixels(imgPanel.getPixels(), checkedAreaPixelNumber);
        System.out.println("Number(%) of green pixels in current image: " + percentOfGreen);
        return percentOfGreen;
    }

    //Calculates how many pixels are in image that are not white ( blank area )
    private int calculateCheckedAreaSize(int[][] pixels) {
        int rowN = pixels.length;
        int colN = pixels[0].length;

        int backgroundRGBColor = -65793;

        int areaPixelNumber = 0;
        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                if (pixels[i][j] != backgroundRGBColor) {
                    areaPixelNumber++;
                }
            }
        }
        return areaPixelNumber;
    }

    private int[][] removeOtherPixelsThanGreen(int[][] pixels) {
        int rowN = pixels.length;
        int colN = pixels[0].length;
        int greenPixelNumber = 0;
        int pixelValue;
        int red, green, blue;
        float[] hsvVals = new float[3];
        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                pixelValue = pixels[i][j];
                red = getRed(pixelValue);
                green = getGreen(pixelValue);
                blue = getBlue(pixelValue);
                Color.RGBtoHSB(red, green, blue, hsvVals);

                if (hsvVals[0] < maxH && hsvVals[0] > minH && hsvVals[1] < minS) { //&& hsvVals[2] >= minB) {
                    greenPixelNumber++;
                } else {
                    pixels[i][j] = -1;
                }
            }
        }

        return pixels;
    }

    private int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    private int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    private int getBlue(int rgb) {
        return (rgb) & 0xFF;
    }

    private double calculateNumberOfBlackPixels(int[][] pixels, double areaPixelNumber) {
        int pixNumber = 0;
        int rowN = pixels.length;
        int colN = pixels[0].length;
        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                if (pixels[i][j] == 0) {
                    pixNumber++;
                }
            }
        }

        return (double) pixNumber / (double) (areaPixelNumber);
    }
}

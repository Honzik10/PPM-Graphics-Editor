/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfology;

import java.awt.Color;
import java.awt.image.ColorModel;
import java.util.Arrays;
import other.PpmPanel;

public class Morfology {

    PpmPanel imgPanel;

    public Morfology(PpmPanel imgPanel) {
        this.imgPanel = imgPanel;
    }

    public int[][] get3x3StructEl() {
        int[][] structEl = new int[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(structEl[i], 1);
        }
        return structEl;
    }

    //Dylacja
    public int[][] dilation(int[][] structuralEl) {
        int[][] pixels = imgPanel.getPixels();
        int rowNumber = pixels.length;
        int[][] newPixels = new int[pixels.length][pixels[0].length];

        int whiteRGB = -1;
        int blackRGB = 0; //Why u do diz java? TODO

        boolean setBlack;
        for (int row = 1; row < rowNumber - 1; row++) {
            for (int column = 1; column < pixels[0].length - 1; column++) {
                setBlack = checkIfStructElContains(structuralEl, row, column);
                if (setBlack) {
                    newPixels[row][column] = blackRGB;
                } else {
                    newPixels[row][column] = whiteRGB;
                }
            }
        }

        return newPixels;
    }

    //TODO
    private boolean equals(int[][] arr1, int[][] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        if (arr1[0].length != arr2[0].length) {
            return false;
        }

        for (int row = 0; row < arr1.length; row++) {
            for (int column = 1; column < arr1[0].length; column++) {
                if(arr1[row][column] != arr2[row][column]){
                    
                }
            }
        }
        
        return false;
    }

    //Structural el size of 2n+1 ( ex. 3x3, 5x5...)
    public int[][] getPixelNeighbours(int row, int column, int[][] structuralEl) {
        int[][] pixels = imgPanel.getPixels();
        int structElHeight = structuralEl.length;

        int startRow = row - (structElHeight - 1) / 2;
        int endRow = row + (structElHeight - 1) / 2;
        int startCol = column - (structElHeight - 1) / 2;
        int endCol = column + (structElHeight - 1) / 2;

        int[][] pixelNeighbours = new int[structElHeight][structElHeight];
        int pixNeighbourRowIndex = 0;
        for (int rowInd = startRow; rowInd <= endRow; rowInd++) {
            pixelNeighbours[pixNeighbourRowIndex] = Arrays.copyOfRange(pixels[rowInd], startCol, endCol);
        }

        return pixelNeighbours;
    }

    public boolean checkIfStructElContains(int[][] structuralEl, int pixelRow, int pixelCol) {
        int structElHeight = structuralEl.length;
        int startRow = pixelRow - (structElHeight - 1) / 2;
        int endRow = pixelRow + (structElHeight - 1) / 2;
        int startCol = pixelCol - (structElHeight - 1) / 2;
        int endCol = pixelCol + (structElHeight - 1) / 2;
        int[][] pixels = imgPanel.getPixels();
        int structElRow = 0, structElCol = 0;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                //Get value of stucturalEl 
                int structElVal = structuralEl[structElRow][structElCol];

                if (structElVal == 1) {
                    int pixelValue = pixels[r][c];
                    if (pixelValue == 0) {
                        //One of neigbour pixels based on structEl is black
                        return true;
                    }
                }
            }
            structElCol = 0;
            structElRow++;
        }

        //None of neigbour pixels based on structEl is black
        return false;
    }
}

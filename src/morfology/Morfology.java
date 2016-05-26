package morfology;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Random;
import other.MyArray;
import other.PpmPanel;

public class Morfology {

    //TODO Opening + CLOSING FIXING!
    PpmPanel imgPanel;

    /*
    Structutal element values:
    0 - black, background
    -1 - white, object pixel
    2 - skipped pixels, not checked. Usued with more variety shapes
     */
    private final int whiteRGB = -1;
    private final int blackRGB = 0;
    private final int skipped = 2;

    public Morfology(PpmPanel imgPanel) {
        this.imgPanel = imgPanel;
    }

    //Dylatacja
    //Saving on copy pixel array, iterating over oryginal pixel array
    public int[][] dilation(int[][] pixelsCopy, StructElement structuralEl) {
        int[][] oryginalPixels = imgPanel.getPixels();
        int rowNumber = pixelsCopy.length;
        int colNumber = pixelsCopy[0].length;

        int skipRowNumber = structuralEl.getRowNumber();
        int skipColNumber = structuralEl.getColumnNumber();
        int centerRow = structuralEl.getCenterRow();
        int centerCol = structuralEl.getCenterCol();
        boolean setBlack;
        for (int row = 0; row <= rowNumber - skipRowNumber; row++) {
            for (int column = 0; column <= colNumber - skipColNumber; column++) {
                setBlack = checkIfStructElContainsColor(oryginalPixels, structuralEl, row, column, blackRGB);
                if (setBlack) {
                    pixelsCopy[row + centerRow][column + centerCol] = blackRGB;
                }
            }
        }

        return pixelsCopy;
    }

    public int[][] erosion(int[][] pixelsCopy, StructElement structuralEl) {
        int[][] oryginalPixels = imgPanel.getPixels();
        int rowNumber = pixelsCopy.length;
        int colNumber = pixelsCopy[0].length;
        int skipRowNumber = structuralEl.getRowNumber();
        int skipColNumber = structuralEl.getColumnNumber();

        int centerRow = structuralEl.getCenterRow();
        int centerCol = structuralEl.getCenterCol();
        boolean setWhite;
        for (int row = 0; row <= rowNumber - skipRowNumber; row++) {
            for (int column = 0; column <= colNumber - skipColNumber; column++) {
                setWhite = checkIfStructElContainsColor(oryginalPixels, structuralEl, row, column, whiteRGB);
                if (setWhite) {
                    pixelsCopy[row + centerRow][column + centerCol] = whiteRGB;
                }
            }
        }

        return pixelsCopy;
    }

    // Dilation on erosion
    public int[][] opening(int[][] pixelsCopy, StructElement structuralEl) {
        int[][] pixelsAfterErosion = erosion(pixelsCopy, structuralEl);
        imgPanel.setPixels(pixelsAfterErosion);
        int[][] erodedPixelsCopy = MyArray.copyArray(pixelsAfterErosion);
        int[][] pixelsAfterOpening = dilation(erodedPixelsCopy, structuralEl);
        return pixelsAfterOpening;
    }

    // Erosion on dilation
    public int[][] closing(int[][] pixelsCopy, StructElement structuralEl) {
        int[][] pixelsAfterDilation = dilation(pixelsCopy, structuralEl);
        imgPanel.setPixels(pixelsAfterDilation);
        int[][] dilatedPixelsCopy = MyArray.copyArray(pixelsAfterDilation);
        int[][] pixelsAfterClosing = erosion(dilatedPixelsCopy, structuralEl);
        return pixelsAfterClosing;
    }

    //Thinning scienianie
    public int[][] thinning(int[][] pixelsCopy, StructElement structuralEl) {
        int[][] oryginalPixels = imgPanel.getPixels();
        int skipRowNumber = structuralEl.getRowNumber();
        int skipColNumber = structuralEl.getColumnNumber();
        int rowNumber = pixelsCopy.length;
        int colNumber = pixelsCopy[0].length;
        int centerRow = structuralEl.getCenterRow();
        int centerCol = structuralEl.getCenterCol();
        boolean thin;
        for (int row = 0; row <= rowNumber - skipRowNumber; row++) {
            for (int column = 0; column <= colNumber - skipColNumber; column++) {
                thin = checkIfStructElContainsHit(oryginalPixels, structuralEl, row, column);
                if (thin) {
                    //pixelsCopy[row + centerRow][column + centerCol] = whiteRGB;
                    pixelsCopy[row][column] = whiteRGB;
                }
            }
        }

        return pixelsCopy;
    }

    //Thickening pogrubianie
    public int[][] thickening(int[][] pixelsCopy, StructElement structuralEl) {
        int[][] oryginalPixels = imgPanel.getPixels();
        int skipRowNumber = structuralEl.getRowNumber();
        int skipColNumber = structuralEl.getColumnNumber();
        int rowNumber = pixelsCopy.length;
        int colNumber = pixelsCopy[0].length;
        int centerRow = structuralEl.getCenterRow();
        int centerCol = structuralEl.getCenterCol();

        boolean thick;
        for (int row = 0; row <= rowNumber - skipRowNumber; row++) {
            for (int column = 0; column <= colNumber - skipColNumber; column++) {

                thick = checkIfStructElContainsHit(oryginalPixels, structuralEl, row, column);
                if (thick) {
                    //pixelsCopy[row + centerRow][column + centerCol] = blackRGB;
                    pixelsCopy[row][column] = blackRGB;
                }
            }
        }

        return pixelsCopy;
    }

    public boolean checkIfStructElContainsColor(int[][] pixels, StructElement structuralEl, int pixelRow, int pixelCol, int rgbColor) {
        int structElHeight = structuralEl.getColumnNumber();
        int startRow = pixelRow;
        int endRow = pixelRow + structElHeight - 1;
        int startCol = pixelCol;
        int endCol = pixelCol + structElHeight - 1;
        int structElRow = 0, structElCol = 0;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                //Get value of stucturalEl 
                int structElVal = structuralEl.getValue(structElRow, structElCol);

                //Skipping pixels to skip (val 2), center ( val 3), 
                if (structElVal != skipped) {
                    int pixelValue = pixels[r][c];
                    if (pixelValue == rgbColor) {
                        //One of neigbour pixels based on structEl is black or white
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

    public boolean checkIfStructElContainsHit(int[][] pixels, StructElement structuralEl, int pixelRow, int pixelCol) {
        int structElHeight = structuralEl.getColumnNumber();
        int startRow = pixelRow;
        int endRow = pixelRow + structElHeight - 1;
        int startCol = pixelCol;
        int endCol = pixelCol + structElHeight - 1;
        int structElRow = 0, structElCol = 0;

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                //Get value of stucturalEl 
                int structElVal = structuralEl.getValue(structElRow, structElCol);

                if (structElVal != skipped) {
                    int pixelValue = pixels[r][c];
                    if (pixelValue != structElVal) {
                        //Pixel not equal to element of structEl.
                        return false;
                    }
                }
            }
            structElCol = 0;
            structElRow++;
        }

        //All checked pixels equal to element of structEl.
        return true;
    }

    public boolean checkForBlackImg(int[][] pixels) {
        int rowN = pixels.length;
        int colN = pixels[0].length;

        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                if (pixels[i][j] == -1) {
                    System.out.println("Image is not all black");
                    return false;
                }
            }
        }

        System.out.println("All checked pixels are BLACK WTF!");
        return true;
    }
}

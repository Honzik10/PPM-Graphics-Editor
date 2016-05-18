package grafikak2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class ImageFilter {

    PpmPanel imagePanel;
    // http://www.algorytm.org/przetwarzanie-obrazow/filtrowanie-obrazow.html

    ArrayList<Integer> currentMaskList;
    ArrayList<Integer> blurMask; //Usredniajacy - wygladzajacy
    ArrayList<Integer> sobelHorizontalMask;
    ArrayList<Integer> sobelVerticalMask;
    ArrayList<Integer> meanRemovalMask; //Gornoprzepustowy wyostrzajacy
    ArrayList<Integer> gaussBlurMask;
    Mask currentMaskType;

    public enum Mask {
        Blur, GaussianBlur, Sharpen, MeanRemoval, SobelHorizontal, SobelVertical, Median; //Median doesnt have mask. Just for informational method!
    }

    public ImageFilter(PpmPanel panel) {
        imagePanel = panel;
        createMasks();
    }

    public void setMask(Mask maskType) {
        currentMaskType = maskType;
        if (null != maskType) {
            switch (maskType) {
                case Blur:
                    currentMaskList = blurMask;
                    break;
                case MeanRemoval:
                    currentMaskList = meanRemovalMask;
                    break;
                case GaussianBlur:
                    currentMaskList = gaussBlurMask;
                    break;
                case SobelHorizontal:
                    currentMaskList = sobelHorizontalMask;
                    break;
                case SobelVertical:
                    currentMaskList = sobelVerticalMask;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Current mask type: " + currentMaskType.toString());
    }

    private void createMasks() {
        blurMask = new ArrayList(9);
        meanRemovalMask = new ArrayList(9);
        gaussBlurMask = new ArrayList(9);
        sobelHorizontalMask = new ArrayList(9);
        sobelVerticalMask = new ArrayList(9);

        for (int i = 0; i < 9; i++) {
            blurMask.add(1);
        }
        gaussBlurMask.add(1);
        gaussBlurMask.add(2);
        gaussBlurMask.add(1);
        gaussBlurMask.add(2);
        gaussBlurMask.add(4);
        gaussBlurMask.add(2);
        gaussBlurMask.add(1);
        gaussBlurMask.add(2);
        gaussBlurMask.add(1);

        for (int i = 1; i <= 9; i++) {
            if (i == 5) {
                meanRemovalMask.add(9);
            } else {
                meanRemovalMask.add(-1);
            }
        }

        sobelHorizontalMask.add(-1);
        sobelHorizontalMask.add(0);
        sobelHorizontalMask.add(1);
        sobelHorizontalMask.add(-2);
        sobelHorizontalMask.add(0);
        sobelHorizontalMask.add(2);
        sobelHorizontalMask.add(-1);
        sobelHorizontalMask.add(0);
        sobelHorizontalMask.add(1);

        sobelVerticalMask.add(1);
        sobelVerticalMask.add(2);
        sobelVerticalMask.add(1);
        sobelVerticalMask.add(0);
        sobelVerticalMask.add(0);
        sobelVerticalMask.add(0);
        sobelVerticalMask.add(-1);
        sobelVerticalMask.add(-2);
        sobelVerticalMask.add(-1);

    }

    public void meanRemovalFilter() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> pixelNeighbourList = rgbToArrayList(row, col, pixels);
                ArrayList<Integer> redValueList = rgbToSingleColorList(pixelNeighbourList, "red");
                ArrayList<Integer> greenValueList = rgbToSingleColorList(pixelNeighbourList, "green");
                ArrayList<Integer> blueValueList = rgbToSingleColorList(pixelNeighbourList, "blue");
                ArrayList<Integer> redMultipliedByMask = multiplyListWithMask(redValueList);
                ArrayList<Integer> greenMultipliedByMask = multiplyListWithMask(greenValueList);
                ArrayList<Integer> blueMultipliedByMask = multiplyListWithMask(blueValueList);

                int newR = calculateAverage(redValueList);
                int newG = calculateAverage(greenValueList);
                int newB = calculateAverage(blueValueList);

                int rgb = getRgb(newR, newG, newB);

                pixels[row][col] = rgb;
                //int middlePixelNewValueRed = calculateAverage(redMultipliedByMask);
                //int middlePixelNewValueGreen = calculateAverage(greenMultipliedByMask);
                //int middlePixelNewValueBlue = calculateAverage(blueMultipliedByMask);
                //int rgb = getRgb(middlePixelNewValueRed, middlePixelNewValueGreen, middlePixelNewValueBlue);
                //pixels[row][col] = rgb;
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    public void filterImage() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> pixelNeighbourList = rgbToArrayList(row, col, pixels);
                ArrayList<Integer> redValueList = rgbToSingleColorList(pixelNeighbourList, "red");
                ArrayList<Integer> greenValueList = rgbToSingleColorList(pixelNeighbourList, "green");
                ArrayList<Integer> blueValueList = rgbToSingleColorList(pixelNeighbourList, "blue");
                ArrayList<Integer> redMultipliedByMask = multiplyListWithMask(redValueList);
                ArrayList<Integer> greenMultipliedByMask = multiplyListWithMask(greenValueList);
                ArrayList<Integer> blueMultipliedByMask = multiplyListWithMask(blueValueList);
                int maskSum = calculateSumInt(currentMaskList);

                int newR = calculateSumInt(redMultipliedByMask) / maskSum;
                int newG = calculateSumInt(greenMultipliedByMask) / maskSum;
                int newB = calculateSumInt(blueMultipliedByMask) / maskSum;

                int rgb = getRgb(newR, newG, newB);

                pixels[row][col] = rgb;
                //int middlePixelNewValueRed = calculateAverage(redMultipliedByMask);
                //int middlePixelNewValueGreen = calculateAverage(greenMultipliedByMask);
                //int middlePixelNewValueBlue = calculateAverage(blueMultipliedByMask);
                //int rgb = getRgb(middlePixelNewValueRed, middlePixelNewValueGreen, middlePixelNewValueBlue);
                //pixels[row][col] = rgb;
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    //Filter image with currently setted mask
    public void filterImage2() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> pixelNeighbourList = rgbToArrayList(row, col, pixels);
                ArrayList<Integer> redValueList = rgbToSingleColorList(pixelNeighbourList, "red");
                ArrayList<Integer> greenValueList = rgbToSingleColorList(pixelNeighbourList, "green");
                ArrayList<Integer> blueValueList = rgbToSingleColorList(pixelNeighbourList, "blue");
                ArrayList<Integer> redMultipliedByMask = multiplyListWithMask(redValueList);
                ArrayList<Integer> greenMultipliedByMask = multiplyListWithMask(greenValueList);
                ArrayList<Integer> blueMultipliedByMask = multiplyListWithMask(blueValueList);

                ArrayList<Integer> rgbListNew = createRgbFromColorLists(redMultipliedByMask, greenMultipliedByMask, blueMultipliedByMask);
                setRgbFromListToArray(rgbListNew, pixels, row - 1, col - 1);
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    //Test
    public void filterImage3() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> pixelNeighbourList = rgbToArrayList(row, col, pixels); // a
                ArrayList<Integer> redValueList = rgbToSingleColorList(pixelNeighbourList, "red"); //fR
                ArrayList<Integer> greenValueList = rgbToSingleColorList(pixelNeighbourList, "green"); // fG
                ArrayList<Integer> blueValueList = rgbToSingleColorList(pixelNeighbourList, "blue"); // fB
                setMask(Mask.SobelHorizontal);
                ArrayList<Integer> redMultipliedByMask = multiplyListWithMask(redValueList);
                ArrayList<Integer> greenMultipliedByMask = multiplyListWithMask(greenValueList);
                ArrayList<Integer> blueMultipliedByMask = multiplyListWithMask(blueValueList);
                setMask(Mask.SobelVertical);
                ArrayList<Integer> redMultipliedByMask2 = multiplyListWithMask(redValueList);
                ArrayList<Integer> greenMultipliedByMask2 = multiplyListWithMask(greenValueList);
                ArrayList<Integer> blueMultipliedByMask2 = multiplyListWithMask(blueValueList);

                int redHorizontalSum = calculateSumInt(redMultipliedByMask);
                int greenHorizontalSum = calculateSumInt(greenMultipliedByMask);
                int blueHorizontalSum = calculateSumInt(blueMultipliedByMask);

                int redVerticalSum = calculateSumInt(redMultipliedByMask2);
                int greenVerticalSum = calculateSumInt(greenMultipliedByMask2);
                int blueVerticalSum = calculateSumInt(blueMultipliedByMask2);

                int redV = (int) Math.sqrt(((redVerticalSum * redVerticalSum) + (redHorizontalSum * redHorizontalSum)));
                int greenV = (int) Math.sqrt(((greenVerticalSum * greenVerticalSum) + (greenHorizontalSum * greenHorizontalSum)));
                int blueV = (int) Math.sqrt(((blueVerticalSum * blueVerticalSum) + (blueHorizontalSum * blueHorizontalSum)));

                int rgb = getRgb(redV, greenV, blueV);
                pixels[row][col] = rgb;
                //ArrayList<Integer> rgbListNew = createRgbFromColorLists(redMultipliedByMask, greenMultipliedByMask, blueMultipliedByMask);
                //setRgbFromListToArray(rgbListNew, pixels, row-1, col-1);
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    //Test2
    public void filterImage4() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> pixelNeighbourList = rgbToArrayList(row, col, pixels); // a
                ArrayList<Integer> redValueList = rgbToSingleColorList(pixelNeighbourList, "red"); //fR
                ArrayList<Integer> greenValueList = rgbToSingleColorList(pixelNeighbourList, "green"); // fG
                ArrayList<Integer> blueValueList = rgbToSingleColorList(pixelNeighbourList, "blue"); // fB

                ArrayList<Integer> redMultipliedByMask = multiplyListWithMask(redValueList);
                ArrayList<Integer> greenMultipliedByMask = multiplyListWithMask(greenValueList);
                ArrayList<Integer> blueMultipliedByMask = multiplyListWithMask(blueValueList);

                int redHorizontalSum = calculateSumInt(redMultipliedByMask);
                int greenHorizontalSum = calculateSumInt(greenMultipliedByMask);
                int blueHorizontalSum = calculateSumInt(blueMultipliedByMask);

                int rgb = getRgb(redHorizontalSum, greenHorizontalSum, blueHorizontalSum);
                pixels[row][col] = rgb / 9;
                //ArrayList<Integer> rgbListNew = createRgbFromColorLists(redMultipliedByMask, greenMultipliedByMask, blueMultipliedByMask);
                //setRgbFromListToArray(rgbListNew, pixels, row-1, col-1);
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    // 0 1 2 
    // 3 4 5
    // 6 7 8
    public void filterImage5() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {

                long pixelX = ((sobelHorizontalMask.get(0) * pixels[row - 1][col - 1]) + (sobelHorizontalMask.get(1) * pixels[row - 1][col]) + (sobelHorizontalMask.get(2) * pixels[row - 1][col + 1])
                        + (sobelHorizontalMask.get(3) * pixels[row][col - 1]) + (sobelHorizontalMask.get(4) * pixels[row][col]) + (sobelHorizontalMask.get(5) * pixels[row][col + 1])
                        + (sobelHorizontalMask.get(6) * pixels[row + 1][col - 1]) + (sobelHorizontalMask.get(7) * pixels[row + 1][col]) + (sobelHorizontalMask.get(8) * pixels[row + 1][col + 1]));
                long pixelY = ((sobelVerticalMask.get(0) * pixels[row - 1][col - 1]) + (sobelVerticalMask.get(1) * pixels[row - 1][col]) + (sobelVerticalMask.get(2) * pixels[row - 1][col + 1])
                        + (sobelVerticalMask.get(3) * pixels[row][col - 1]) + (sobelVerticalMask.get(4) * pixels[row][col]) + (sobelVerticalMask.get(5) * pixels[row][col + 1])
                        + (sobelVerticalMask.get(6) * pixels[row + 1][col - 1]) + (sobelVerticalMask.get(7) * pixels[row + 1][col]) + (sobelVerticalMask.get(8) * pixels[row + 1][col + 1]));

//                ArrayList<Integer> red = rgbToArrayList(row, col, pixels);
//                ArrayList<Integer> green = rgbToArrayList(row, col, pixels);
//                ArrayList<Integer> blue = rgbToArrayList(row, col, pixels);
//                
//                ArrayList<Long> mRed = multiplyListWithMask(red, sobelVerticalMask);
//                ArrayList<Long> mGreen = multiplyListWithMask(green, sobelVerticalMask);
//                ArrayList<Long> mBlue = multiplyListWithMask(blue, sobelVerticalMask);
//                
//                ArrayList<Long> mRedH = multiplyListWithMask(red, sobelHorizontalMask);
//                ArrayList<Long> mGreenH = multiplyListWithMask(green, sobelHorizontalMask);
//                ArrayList<Long> mBlueH = multiplyListWithMask(blue, sobelHorizontalMask);
//                
//                long redSum = calculateSum(mRed);
//                long greenSum = calculateSum(mGreen);
//                long blueSum = calculateSum(mBlue);
//                long redSumH = calculateSum(mRedH);
//                long greenSumH = calculateSum(mGreenH);
//                long blueSumH = calculateSum(mBlueH);
//                
//                long redC = (long) Math.hypot(redSum, redSumH);
//                long greenC = (long) Math.hypot(greenSum, greenSumH);
//                long blueC = (long) Math.hypot(blueSum, blueSumH);
//                
//                int redC2 = ImageLookupTable.checkColorBounds((int) redC);
//                int greenC2 = ImageLookupTable.checkColorBounds((int) greenC);
//                int blueC2 = ImageLookupTable.checkColorBounds((int) blueC);
//                
//                int rgb = getRgb(redC2, greenC2, blueC2);
                long pixelX2 = ((-1 * pixels[row - 1][col - 1]) + (pixels[row - 1][col + 1]) + (-2 * pixels[row][col - 1]) + (2 * pixels[row][col + 1]) + (-1 * pixels[row + 1][col - 1]) + (pixels[row + 1][col + 1]));
                long pixelY2 = ((pixels[row - 1][col - 1]) + (2 * pixels[row - 1][col]) + (pixels[row - 1][col + 1]) + (-1 * pixels[row + 1][col - 1]) + (-2 * pixels[row + 1][col]) + (-1 * pixels[row + 1][col + 1]));
                long value1 = (long) Math.hypot(pixelX, pixelY); //Sposob 1
                //Sposob 2
                long pixelXsquare = pixelX2 * pixelX2;
                long pixelYsquare = pixelY2 * pixelY2;
                long squareSum = pixelXsquare + pixelYsquare;
                long value2 = (long) Math.sqrt(squareSum);
                //Sposob 3
                long powX = (long) Math.pow(pixelX2, 2);
                long powY = (long) Math.pow(pixelY2, 2);
                long powSum = powX + powY;
                long value3 = (long) Math.sqrt(powSum);

                //Spr czy wszystkie sposoby za kazdym razem zwracaja ta sama wartosc
                if (value1 != value2 || value1 != value3) {
                    System.out.println("Difference");
                }

                //
                //Sprawdzenie czy nie tracimy danych rzutujac long na int
                //if value3 > maxInt
                if (value3 > Integer.MAX_VALUE) {
                    System.out.println("Utrata danych");

                }
//                if(value1 > 16777216){
//                    value1 = 16777216;
//                }else if(value1 <0){
//                    value3 = 0;
//                }

                pixels[row][col] = (int) value1;
                //pixels[row][col] = rgb;
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    public void filterImage6() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();
        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                
                ArrayList<Integer> rgb = rgbToArrayList(row, col, pixels);
                ArrayList<Integer> red = rgbToSingleColorList(rgb, "red");
                ArrayList<Integer> green = rgbToSingleColorList(rgb, "green");
                ArrayList<Integer> blue = rgbToSingleColorList(rgb, "blue");
                
                ArrayList<Long> mRed = multiplyListWithMask(red, sobelVerticalMask);
                ArrayList<Long> mGreen = multiplyListWithMask(green, sobelVerticalMask);
                ArrayList<Long> mBlue = multiplyListWithMask(blue, sobelVerticalMask);
                
                ArrayList<Long> mRedH = multiplyListWithMask(red, sobelHorizontalMask);
                ArrayList<Long> mGreenH = multiplyListWithMask(green, sobelHorizontalMask);
                ArrayList<Long> mBlueH = multiplyListWithMask(blue, sobelHorizontalMask);
                
                long redSum = calculateSum(mRed);
                long greenSum = calculateSum(mGreen);
                long blueSum = calculateSum(mBlue);
                long redSumH = calculateSum(mRedH);
                long greenSumH = calculateSum(mGreenH);
                long blueSumH = calculateSum(mBlueH);
                
                long redC = (long) Math.hypot(redSum, redSumH);
                long greenC = (long) Math.hypot(greenSum, greenSumH);
                long blueC = (long) Math.hypot(blueSum, blueSumH);
                
//                long newRed = (redC + greenC + blueC)/3;
//                long newGreen = newRed;
//                long newBlue = newRed;
                long newRed = redC;
                long newGreen = greenC;
                long newBlue = blueC;
                
                int redC2 = ImageLookupTable.checkColorBounds((int) newRed);
                int greenC2 = ImageLookupTable.checkColorBounds((int) newGreen);
                int blueC2 = ImageLookupTable.checkColorBounds((int) newBlue);
                
                //int rgbC = getRgb(redC2, greenC2, blueC2);         
                int rgbC = getRgb((int) redC2,(int) greenC2,(int) blueC2); 
                
                pixels[row][col] = rgbC;
                //pixels[row][col] = rgb;
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    private ArrayList<Integer> createRgbFromColorLists(ArrayList<Integer> r, ArrayList<Integer> g, ArrayList<Integer> b) {
        ArrayList<Integer> rgbList = new ArrayList(9);
        int currRed, currGreen, currBlue, currRgb;
        for (int i = 0; i < 9; i++) {
            currRed = r.get(i);
            currGreen = g.get(i);
            currBlue = b.get(i);
            currRgb = getRgb(currRed, currGreen, currBlue);
            rgbList.add(currRgb);
        }
        return rgbList;
    }

    private void setRgbFromListToArray(ArrayList<Integer> rgbList, int[][] pixels, int startRow, int startCol) {
        int row = startRow;
        int col = startCol;
        for (int i : rgbList) {
            pixels[row][col] = i;
            col++;
            if (col > startCol + 2) {
                col = startCol;
                row++;
            }
        }
    }

    //Multiplies single element of mask list with single el of given list
    private ArrayList<Integer> multiplyListWithMask(ArrayList<Integer> list) {
        ArrayList<Integer> resultList = new ArrayList(9);
        for (int i = 0; i < list.size(); i++) {
            int val = list.get(i) * currentMaskList.get(i);
            resultList.add(val);
        }

        return resultList;
    }

    private ArrayList<Long> multiplyListWithMask(ArrayList<Integer> list, ArrayList<Integer> mask) {
        ArrayList<Long> resultList = new ArrayList(9);
        for (int i = 0; i < list.size(); i++) {
            long val = (long) list.get(i) * (long) mask.get(i);
            resultList.add(val);
        }

        return resultList;
    }

    //Skips borderline pixels
    // 1 2 3
    // 4 5 6
    // 7 8 9
    public void averagingFilter() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();

        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                int rgb1 = pixels[row - 1][col - 1];
                int rgb2 = pixels[row - 1][col];
                int rgb3 = pixels[row - 1][col + 1];
                int rgb4 = pixels[row][col - 1];
                int rgb5 = pixels[row][col]; // Middle pixel
                int rgb6 = pixels[row][col + 1];
                int rgb7 = pixels[row + 1][col - 1];
                int rgb8 = pixels[row + 1][col];
                int rgb9 = pixels[row + 1][col + 1];

                int avgRed = (getRed(rgb1) + getRed(rgb2) + getRed(rgb3) + getRed(rgb4) + getRed(rgb5) + getRed(rgb6) + getRed(rgb7) + getRed(rgb8) + getRed(rgb9)) / 9;
                int avgGreen = (getGreen(rgb1) + getGreen(rgb2) + getGreen(rgb3) + getGreen(rgb4) + getGreen(rgb5) + getGreen(rgb6) + getGreen(rgb7) + getGreen(rgb8) + getGreen(rgb9)) / 9;
                int avgBlue = (getBlue(rgb1) + getBlue(rgb2) + getBlue(rgb3) + getBlue(rgb4) + getBlue(rgb5) + getBlue(rgb6) + getBlue(rgb7) + getBlue(rgb8) + getBlue(rgb9)) / 9;
                int newRgb = getRgb(avgRed, avgGreen, avgBlue);
                pixels[row][col] = newRgb;
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    public void medianFilter() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();

        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> rgbList = rgbToArrayList(row, col, pixels);
                ArrayList<Integer> redList = rgbToSingleColorList(rgbList, "red");
                ArrayList<Integer> greenList = rgbToSingleColorList(rgbList, "green");
                ArrayList<Integer> blueList = rgbToSingleColorList(rgbList, "blue");

                int medRed = getMedian(redList);
                int medGreen = getMedian(greenList);
                int medBlue = getMedian(blueList);

                int newRgb = getRgb(medRed, medGreen, medBlue);
                pixels[row][col] = newRgb;
            }
        }
        displayFilteringMessage();
        imagePanel.displayImage();
    }

    private ArrayList<Integer> rgbToArrayList(int row, int col, int pixels[][]) {
        ArrayList<Integer> color = new ArrayList(9);

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                color.add(pixels[i][j]);
            }
        }
        return color;
    }

    private ArrayList<Integer> rgbToSingleColorList(ArrayList<Integer> rgbList, String color) {
        ArrayList<Integer> singeColorList = new ArrayList(9);

        switch (color) {
            default:
                System.out.println("Wrong color in argument!");
                break;
            case "red":
                for (int i : rgbList) {
                    int currRed = getRed(i);
                    singeColorList.add(currRed);
                }
                break;
            case "green":
                for (int i : rgbList) {
                    int currRed = getGreen(i);
                    singeColorList.add(currRed);
                }
                break;
            case "blue":
                for (int i : rgbList) {
                    int currRed = getBlue(i);
                    singeColorList.add(currRed);
                }
                break;
        }

        return singeColorList;
    }

    private int getMedian(ArrayList<Integer> list) {
        Collections.sort(list);
        int listSize = list.size();

        if (listSize % 2 == 0) {
            int index2 = listSize / 2;
            int index1 = index2 - 1;
            int medVal1 = list.get(index2);
            int medVal2 = list.get(index1);
            return (medVal1 + medVal2) / 2;
        } else {
            int index = listSize / 2;
            return list.get(index);
        }
    }

    public void sobelFilter() {
        int pixels[][] = imagePanel.getPixels();
        int maxRow = imagePanel.getOryginalHeight();
        int maxCol = imagePanel.getOryginalWidth();

        for (int row = 1; row < maxRow - 1; row++) {
            for (int col = 1; col < maxCol - 1; col++) {
                ArrayList<Integer> rgbList = rgbToArrayList(row, col, pixels);
//                ArrayList<Integer> redList = rgbToSingleColorList(rgbList, "red");
//                ArrayList<Integer> greenList = rgbToSingleColorList(rgbList, "green");
//                ArrayList<Integer> blueList = rgbToSingleColorList(rgbList, "blue");

//                int sobRed = calculateSobelValue(redList);
//                int sobGreen = calculateSobelValue(greenList);
//                int sobBlue = calculateSobelValue(blueList);
                int rgbSob = calculateSobelValue(rgbList);

                //int newRgb = getRgb(sobRed, sobGreen, sobBlue);
                pixels[row][col] = rgbSob;
            }
        }
        imagePanel.displayImage();
    }

    // 0 1 2 - Normal 
    // 3 4 5
    // 6 7 8
//    p0   p1   p2 - Sobel
//    p7   px   p3
//    p6   p5   p4
    private int calculateSobelValue(ArrayList<Integer> singleColorList) {
        int p0 = singleColorList.get(0);
        int p1 = singleColorList.get(1);
        int p2 = singleColorList.get(2);
        int p3 = singleColorList.get(5);
        int p4 = singleColorList.get(8);
        int p5 = singleColorList.get(7);
        int p6 = singleColorList.get(6);
        int p7 = singleColorList.get(3);
        int x = (p2 + 2 * p3 + p4) - (p0 + 2 * p7 + p6);
        int y = (p6 + 2 * p5 + p4) - (p0 + 2 * p1 + p2);

        double powValue = (x * x) + (y * y);
        double sqrtValue = Math.sqrt(powValue);
        int val = Math.round((float) sqrtValue);
        return val;
    }

    private long calculateSum(ArrayList<Long> list) {
        long sum = 0;
        for (long i : list) {
            sum += i;
        }
        return sum;
    }

    private int calculateSumInt(ArrayList<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    private int calculateAverage(ArrayList<Integer> list) {
        int sum = calculateSumInt(list);
        return sum / (list.size());
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

    private int getRgb(int r, int g, int b) {
        return 65536 * r + 256 * g + b;
    }

    //Displays message in console for conrete filter
    private void displayFilteringMessage() {
        String completeMessage = "Image filtering complete with ";
        if (currentMaskType.compareTo(ImageFilter.Mask.Blur) == 0) {
            completeMessage += "BLUR FILTER.";
        } else if (currentMaskType.compareTo(ImageFilter.Mask.GaussianBlur) == 0) {
            completeMessage += "GAUSSIAN BLUR FILTER.";
        } else if (currentMaskType.compareTo(ImageFilter.Mask.MeanRemoval) == 0) {
            completeMessage += "MEAN REMOVAL FILTER.";
        } else if (currentMaskType.compareTo(ImageFilter.Mask.SobelHorizontal) == 0) {
            completeMessage += "SOBEL HORIZONTAL FILTER.";
        } else if (currentMaskType.compareTo(ImageFilter.Mask.SobelVertical) == 0) {
            completeMessage += "SOBEL VERTICAL FILTER.";
        } else if (currentMaskType.compareTo(ImageFilter.Mask.Median) == 0) {
            completeMessage += "MEDIAN FILTER";
        }

        System.out.println(completeMessage);
    }

}

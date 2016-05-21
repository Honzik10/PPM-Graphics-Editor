/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

import java.util.ArrayList;

/**
 *
 * @author honzik
 */
public class ImageLookupTable {

    PpmPanel panel = null;
    ArrayList<ArrayList> addLookupTable = new ArrayList(256);
    ArrayList<ArrayList> subtractLookupTable = new ArrayList(256);
    ArrayList<ArrayList> multiplyLookupTable = new ArrayList(256);
    ArrayList<ArrayList> divideLookupTable = new ArrayList(256);
    ArrayList<ArrayList> brightnessLookupTable = new ArrayList(256);
    ArrayList<ArrayList> darknessLookupTable = new ArrayList(256);
    ArrayList<ArrayList> grayScaleLookupTable = new ArrayList(256);
    ArrayList<ArrayList> grayScaleLookupTable2 = new ArrayList(256);

    public ArrayList<ArrayList> getBrightnessLookupTable() {
        return brightnessLookupTable;
    }

    public ArrayList<ArrayList> getDarknessLookupTable() {
        return darknessLookupTable;
    }

    public ArrayList<ArrayList> getAddLookupTable() {
        return addLookupTable;
    }

    public ArrayList<ArrayList> getSubtractLookupTable() {
        return subtractLookupTable;
    }

    public ArrayList<ArrayList> getMultiplyLookupTable() {
        return multiplyLookupTable;
    }

    public ArrayList<ArrayList> getDivideLookupTable() {
        return divideLookupTable;
    }

    public ImageLookupTable(PpmPanel panel) {
        this.panel = panel;
        createLUT();
    }

    private void createLUT() {
        createAddLUT();
        createSubtractLUT();
        createMultiplyLUT();
        createDivisionLUT();
        createBrighteningLUT();
        createDarkeningLUT();
        //createDarkscaleLUT();
    }

    private void createAddLUT() {
        ArrayList<Integer> currAddList;
        int value;
        for (int i = 0; i < 256; i++) {
            currAddList = new ArrayList(256);
            for (int j = 0; j < 256; j++) {
                value = checkColorBounds(i + j);
                currAddList.add(value);
                //System.out.println("Kolor " + i + " + " + j + " = " + value);
            }
            addLookupTable.add(currAddList);
        }
    }

    private void createSubtractLUT() {
        ArrayList<Integer> currSubList;
        int value;
        for (int i = 0; i < 256; i++) {
            currSubList = new ArrayList(256);
            for (int j = 0; j < 256; j++) {
                value = checkColorBounds(i - j);
                currSubList.add(value);
                //System.out.println("Kolor " + i + " - " + j + " = " + value);
            }
            subtractLookupTable.add(currSubList);
        }
    }

    private void createMultiplyLUT() {
        ArrayList<Integer> currMulList;
        int value;
        for (int i = 0; i < 256; i++) {
            currMulList = new ArrayList(256);
            for (int j = 0; j < 256; j++) {
                value = checkColorBounds(i * j);
                currMulList.add(value);
            }
            multiplyLookupTable.add(currMulList);
        }
    }

    private void createDivisionLUT() {
        ArrayList<Integer> currSubList;
        int value;
        for (int i = 0; i < 256; i++) {
            currSubList = new ArrayList(256);
            for (int j = 1; j < 256; j++) {
                value = checkColorBounds(i / j);
                currSubList.add(value);
            }
            divideLookupTable.add(currSubList);
        }
    }

    private void createBrighteningLUT() {
        ArrayList<Integer> currBrList;
        int value;
        for (int i = 0; i < 256; i++) {
            currBrList = new ArrayList(256);
            for (int j = 0; j < 256; j++) {
                value = checkColorBounds(i - j);
                currBrList.add(value);
            }
            brightnessLookupTable.add(currBrList);
        }
    }

    private void createDarkeningLUT() {
        ArrayList<Integer> currBrList;
        int value;
        for (int i = 0; i < 256; i++) {
            currBrList = new ArrayList(256);
            for (int j = 0; j < 256; j++) {
                value = checkColorBounds(i + j);
                currBrList.add(value);
            }
            darknessLookupTable.add(currBrList);
        }
    }

    private void createDarkscaleLUT() {
        ArrayList<ArrayList> innerList1;
        ArrayList<Integer> innerList2;
        int value;
        for (int i = 0; i < 256; i++) {
            innerList1 = new ArrayList(256);
            for (int j = 0; j < 256; j++) {
                innerList2 = new ArrayList(256);
                for (int k = 0; k < 256; j++) {
                    value = checkColorBounds((i + j + k) / 3);
                    innerList2.add(value);
                }
                innerList1.add(innerList2);
            }
            darknessLookupTable.add(innerList1);
        }
    }

    private void createDarkscaleLUT2() {

    }

    //0-255
    public static int checkColorBounds(int colorValue) {
        if (colorValue > 255) {
            return 255;
        } else if (colorValue < 0) {
            return 0;
        } else {
            return colorValue;
        }
    }

}

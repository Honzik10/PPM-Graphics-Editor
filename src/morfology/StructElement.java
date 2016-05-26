/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morfology;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextField;

/**
 *
 * @author honzik
 */
public class StructElement {

    private int[][] structEl;
    private final int centerRow;
    private final int centerCol;
    private final int whiteRGB = -1;
    private final int blackRGB = 0;
    private final int skipped = 2;

    public StructElement(int rowN, int colN, int centerRow, int centerCol) {
        structEl = new int[rowN][colN];
        this.centerCol = centerCol;
        this.centerRow = centerRow;
    }

    public void fillStructElement(Component[] component) {
        int rowN = structEl.length;
        int colN = structEl[0].length;
        int componentIndex = 0;

        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                Component currComponent = component[componentIndex];
                if (currComponent instanceof JTextField) {
                    Color c = ((JTextField) currComponent).getBackground();
                    if (c == Color.WHITE) {
                        structEl[i][j] = whiteRGB;
                    } else if (c == Color.BLACK) {
                        structEl[i][j] = blackRGB;
                    } else if (c == Color.RED) {
                        structEl[i][j] = skipped;
                    }
                }

                componentIndex++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        int rowN = structEl.length;
        int colN = structEl[0].length;
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("[");
        String parsedNumber;
        for (int i = 0; i < rowN; i++) {
            stringBuilder.append("(");
            for (int j = 0; j < colN; j++) {
                parsedNumber = structElementToString(structEl[i][j]);
                stringBuilder.append(parsedNumber + ",");
            }
            stringBuilder.append(")");
            stringBuilder.append(System.getProperty("line.separator"));
        }
        stringBuilder.append("]");
        stringBuilder.append(System.getProperty("line.separator"));
        stringBuilder.append("CENTER: " + centerRow + "," + centerCol);

        return stringBuilder.toString();
    }

    private String structElementToString(int element) {
        switch (element) {
            case whiteRGB:
                return "WHITE";
            case blackRGB:
                return "BLACK";
            case skipped:
                return "SKIP";
            default:
                return "STH WENT WRONG BLYAT";
        }
    }

    public int getCenterRow() {
        return centerRow;
    }

    public int getCenterCol() {
        return centerCol;
    }

    public int getRowNumber() {
        return structEl.length;
    }

    public int getColumnNumber() {
        return structEl[0].length;
    }

    public int getValue(int row, int column) {
        return structEl[row][column];
    }

}

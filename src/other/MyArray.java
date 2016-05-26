/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

/**
 *
 * @author honzik
 */
public class MyArray {

    public static boolean equals(int[][] arr1, int[][] arr2) {
        int rowN = arr1.length;
        int colN = arr1[0].length;

        if (rowN != arr2.length) {
            return false;
        }

        if (colN != arr2[0].length) {
            return false;
        }

        for (int i = 0; i < rowN; i++) {
            for (int j = 0; j < colN; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[][] copyArray(int[][] array) {
        int rowN = array.length;
        int colN = array[0].length;
        int[][] newArr = new int[rowN][colN];

        for (int i = 0; i < rowN; i++) {
            System.arraycopy(array[i], 0, newArr[i], 0, colN);
        }

        int newRowN = newArr.length;
        int newColN = newArr[0].length;
        
        return newArr;
    }
}

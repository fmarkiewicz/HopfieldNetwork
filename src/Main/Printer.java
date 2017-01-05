/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Sketch.Cell;

/**
 *
 * @author Hayab_000
 */
public class Printer {

    static void printMatrix(Cell[][] cell) {
        int length = cell.length;
        int[][] matrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = cell[i][j].active;
            }
        }
        printMatrix(matrix);
    }

    static public void printMatrix(double[][] m) {
        try {
            int rows = m.length;
            int columns = m[0].length;
            String str = "|";
            System.out.println("Print Matrix");
            for (int i = 0; i < rows; i++) {
                System.out.print(str);
                for (int j = 0; j < columns; j++) {
                    int a = m[i][j] == 1 ? 1 : 0;
                    System.out.print(a);
                }
                System.out.println("|");
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
        System.out.println("------------------------------------------");

    }
    
    static public void printMatrix(int[][] m) {
        try {
            int rows = m.length;
            int columns = m[0].length;
            String str = "|";
            System.out.println("Print Matrix");
            for (int i = 0; i < rows; i++) {
                System.out.print(str);
                for (int j = 0; j < columns; j++) {
                    int a = m[i][j] == 1 ? 1 : 0;
                    System.out.print(a);
                }
                System.out.println("|");
            }

        } catch (Exception e) {
            System.out.println("Matrix is empty!!");
        }
        System.out.println("------------------------------------------");

    }

    static void printVector(double[] vector) {
        System.out.println("Print Vector as Matrix");
        for (int i = 0; i < vector.length; i++) {
            if (i % 40 == 0 && i != 0) {
                System.out.println("");
            }
            System.out.print(vector[i]);
        }
        System.out.println("");
        System.out.println("------------------------------------------");
    }

    static void printVector(int[] vector) {
        System.out.println("Print Vector as Matrix");
        for (int i = 0; i < vector.length; i++) {
            int a = vector[i] == 1 ? 1 : 0;
//            int a = vector[i];
            if (i % 40 == 0 && i != 0) {
                System.out.println("");
            }
            System.out.print(a);
        }
        System.out.println("");
        System.out.println("------------------------------------------");
    }
}

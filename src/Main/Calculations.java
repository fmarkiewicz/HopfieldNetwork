/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Sketch.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Hayab_000
 */
public class Calculations {

    static public Cell[][] copyGrid(Cell[][] from, Cell[][] to) {
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from[0].length; j++) {
                to[i][j].active = from[i][j].active;
            }
        }
        return to;
    }

    public static int signum(double sum) {
        return sum > 0 ? 1 : -1;
    }

    static public double sumWeights(double[] weights, int[] input) {
        double sum = 0;
//      for bias
//        for (int i = 0; i < input.length; i++) {
//            sum += weights[i + 1] * input[i];
//        }
//        sum += weights[0];
        for (int i = 0; i < input.length; i++) {
            sum += weights[i] * input[i];
        }
        return sum;
    }

    static public double[] matrixToVector(double[][] matrix) {
        //konwertuje pojedyncza macierz przykladu do wektora przykladu
        double[] vector = new double[matrix.length * matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                vector[i * matrix[0].length + j] = matrix[i][j];
            }
        }
//        Printer.printVector(vector);
        return vector;
    }

    static public int[] matrixToVector(Cell[][] matrix) {
        //konwertuje pojedyncza macierz przykladu do wektora przykladu
        int[] vector = new int[matrix.length * matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                vector[i * matrix[0].length + j] = matrix[i][j].active;
            }
        }
//        Printer.printVector(vector);
        return vector;
    }

    static public List<double[]> matrixListToVectorList(List<double[][]> matrixList) {
        //konwertuje liste macierzy przykladow do listy wektorow przykladow
        List<double[]> allImgV = new ArrayList<>();
        for (double[][] m : matrixList) {
            allImgV.add(Calculations.matrixToVector(m));
        }
        return allImgV;
    }

    static public List<double[]> addBiasToVector(List<double[]> vectors) {
        // dodaje na pcozatku wektorow przykladow obciazenie rowne 1
        List<double[]> newExamples = new ArrayList<>();
        for (double[] vector : vectors) {
            double[] tmp = new double[vector.length + 1];
            tmp[tmp.length - 1] = 1;
            for (int i = 0; i < vector.length; i++) {
                tmp[i] = vector[i];
            }
            newExamples.add(tmp);
        }
        return newExamples;
    }

//    static public double getThreshold() {
//        // zwraca threshold z zakresu <-0.5; 0.5>
//        double th;
//        Random rand = new Random();
//        th = rand.nextDouble() - 0.5;
//        return th;
//    }
//    static public double[] getWeightsWithBias(int vectorLength) {
////        , double threshold
//        Random rand = new Random();
//        double[] weights = new double[vectorLength];
////        weights[0] = -threshold;
//        for (int i = 0; i < vectorLength; i++) {
//            weights[i] = (rand.nextInt() % 100);
//        }
//        return weights;
//    }
}

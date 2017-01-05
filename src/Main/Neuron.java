/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Random;

/**
 *
 * @author Hayab_000
 */
public class Neuron {

    public double[] weigths; 
    public Neuron() {
        Random rand = new Random();
        double[] tmpWeigths = new double[1601];
//        tmpWeigths[0] = rand.nextInt() % 100;
        for (int i = 0; i < tmpWeigths.length; i++) {
            tmpWeigths[i] = (double)(rand.nextInt() % 100) /100;
        }
        this.weigths = tmpWeigths;
//        Printer.printVector(this.weigths);
    }
    
    public double weightedSum(double[] data){
        double weightedSum = 0;
        
        for (int i=0; i< data.length; i++){
            weightedSum += weigths[i] * data[i];
        }
        return weightedSum;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Hayab_000
 */
public class LinearMachine {

    public Neuron neuronForBlack = new Neuron();
    public Neuron neuronForWhite = new Neuron();
    public double zm = 0.2;

    public int getValue(double[] data) {
        return neuronForBlack.weightedSum(data) > neuronForWhite.weightedSum(data) ? 1 : -1;
    }

    public void learn(double[] example, int isActive) {
        int active = neuronForBlack.weightedSum(example) > neuronForWhite.weightedSum(example) ? 1 : -1;
//        double[] tmpExample = new double[example.length + 1];
        
        if (active != isActive) {
            if (active == 1) {
                for (int i = 0; i < neuronForBlack.weigths.length; i++) {
                    neuronForWhite.weigths[i] += zm * example[i];
                    neuronForBlack.weigths[i] -= zm * example[i];
                }
            } else {
                for (int i = 0; i < neuronForBlack.weigths.length; i++) {
                    neuronForWhite.weigths[i] -= zm * example[i];
                    neuronForBlack.weigths[i] += zm * example[i];
                }
            }
        }
    }
}

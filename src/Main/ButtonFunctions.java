/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Main.Sketch.Cell;
import Main.Sketch.MyButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Yumis
 */
public class ButtonFunctions {

    public void learn() {
        System.out.println("learn");
        List<double[][]> allImgM = ImageManager.loadImages();
        List<double[]> allImgV = new ArrayList<>();
        allImgV = Calculations.matrixListToVectorList(allImgM);
//        for (double[][] example : allImgM) {
//            allImgV.add(Calculations.matrixToVector(example));
//        }
        for (int i = 0; i < Sketch.network.points; i++) {
            for (int j = 0; j < Sketch.network.points; j++) {
                int weight = 0;
                if (i != j) {
                    for (double[] vector : allImgV) {
                        weight += vector[i] * vector[j];
                    }
                }
                
                Sketch.network.weight[i][j] = weight;
            }
        }
        Printer.printMatrix(Sketch.network.weight);
        
    }

    public Cell[][] answer(Cell[][] grid) {
        System.out.println("asnwer");
        int[] input = Calculations.matrixToVector(grid);
        setInput(input);
        System.out.println(Sketch.network.status);
        asynCor();
        input = getOutput(input);
        System.out.println(Sketch.network.status);
        Printer.printVector(input);
        for (int i = 0; i< input.length; i++) {
            grid[(int)i/40][i%40].active = input[i];
        }
        
        return grid;
        
    }

    void setInput(int[] input) {
        for (int i = 0; i < Sketch.network.points; i++) {
            Sketch.network.output[i] = input[i];
        }
        Sketch.network.status = "after setInput";
    }

    int[] getOutput(int[] output) {
        for (int i = 0; i < Sketch.network.points; i++) {
            output[i] = Sketch.network.output[i];
        }
        Sketch.network.status = "after getOutput";
        return output;
    }

    boolean nextIteration(int i) {
        int sum = 0, out = 0;
        boolean changed = false;

        for (int j = 0; j < Sketch.network.points; j++) {
            sum += Sketch.network.weight[i][j] * Sketch.network.output[j];
        }
        
        if(sum != Sketch.network.threshold[i]) {
            if( sum < Sketch.network.threshold[i]) {
                out = -1;
            }
            if( sum > Sketch.network.threshold[i]) {
                out = 1;
            }
            if(out != Sketch.network.output[i]) {
                changed = true;
                Sketch.network.output[i] = out;
            }
        }
        return changed;
    }

    void asynCor() {
        Random rand = new Random();
        int iteration = 0;
        int iterationOfLastChange = 0;
        
        do {
            iteration++;
            
            if(nextIteration(rand.nextInt(Sketch.network.points))) {
                iterationOfLastChange = iteration;
            }
        } while (iteration-iterationOfLastChange < 40*Sketch.network.points);
    }
    
    public void save(Cell[][] grid) {
        ImageManager.saveImage(grid);
    }

    public void clear(Sketch.Cell[][] grid, int rows, int cols) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j].setInactive();
            }
        }
    }

    public void copyToEdit(Cell[][] from, Cell[][] to) {
        for (int i = 0; i < from.length; i++) {
            for (int j = 0; j < from.length; j++) {
                to[i][j].active = from[i][j].active;
                to[i][j].display();
            }
        }
    }

    public boolean btnClicked(MyButton button, int mouseX, int mouseY) {
        return isOverRec(button.x, button.y, button.width, button.height, mouseX, mouseY);
    }

    public boolean cellClicked(Cell cell, int mouseX, int mouseY) {
        return isOverRec(cell.x, cell.y, cell.w, cell.h, mouseX, mouseY);
    }

    public boolean isOverRec(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + width
                && mouseY > y && mouseY < y + height;
    }

}

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

/**
 *
 * @author Yumis
 */
public class ButtonFunctions {

    public List<LinearMachine> learn(List<LinearMachine> LinearMachines) {
        System.out.println("learn");
        List<double[][]> allImgM = ImageManager.loadImages();
        List<double[]> allImgV = new ArrayList<>();
        List<double[]> allImgVWithBias = new ArrayList<>();
        for (double[][] example : allImgM) {
            allImgV.add(Calculations.matrixToVector(example));
//            Printer.printVector(Calculations.matrixToVector(example));
//            double[] imgV = Calculations.matrixToVector(example);
//            allImgVWithBias.add(Calculations.addBiasToVector(imgV));
        }
        allImgVWithBias = Calculations.addBiasToVector(allImgV);
        for (int i = 0; i < 250; i++) {
            for (double[] example : allImgVWithBias) {
                for (int j = 0; j < example.length; j++) {
                    if (example[j] == 1) {
                        LinearMachines.get(j).learn(example, 1);
                    } else {
                        LinearMachines.get(j).learn(example, -1);
                    }
                }
            }
        }
        return LinearMachines;
    }

    public double[] answer(List<LinearMachine> LinearMachines, Cell[][] grid) {
        System.out.println("asnwer");
        int size = grid.length * grid[0].length;
        double[] input = new double[size];
        double[] output = new double[size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                input[i * grid[0].length + j] = grid[i][j].active;
            }
        }

        for (int i = 0; i < size; i++) {
            if (LinearMachines.get(i).getValue(input) == 1) {
                output[i] = 1;
            } else {
                output[i] = -1;
            }
        }
        return output;
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

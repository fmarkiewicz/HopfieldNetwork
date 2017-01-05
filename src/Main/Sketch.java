
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.sin;

public class Sketch extends PApplet {

    ButtonFunctions btn = new ButtonFunctions();

    Cell[][] grid;
    Cell[][] grid2;
    int gridX0 = 0;
    int gridY0 = 0;
    int cellWidth = 10;
    int cellHeight = 10;
    int cols = 40;
    int rows = 40;

    boolean draw = true;

    int grid2X0 = cols * cellWidth + 50;
    int grid2Y0 = 0;

    MyButton clearButton;
    int clearButtonX0 = gridX0 + 50;
    int clearButtonY0 = cols * cellHeight + 50;
    int clearButtonWidth = 100;
    int clearButtonHeight = 30;

    MyButton learnButton;
    int learnButtonX0 = clearButtonX0;
    int learnButtonY0 = clearButtonY0 + clearButtonHeight;
    int learnButtonWidth = clearButtonWidth;
    int learnButtonHeight = clearButtonHeight;

    MyButton recognizeButton;
    int recognizeButtonX0 = learnButtonX0;
    int recognizeButtonY0 = learnButtonY0 + learnButtonHeight;
    int recognizeButtonWidth = learnButtonWidth;
    int recognizeButtonHeight = learnButtonHeight;

    MyButton recognize10TimesButton;
    int recognize10TimesButtonX0 = learnButtonX0;
    int recognize10TimesButtonY0 = recognizeButtonY0 + recognizeButtonHeight;
    int recognize10TimesButtonWidth = learnButtonWidth;
    int recognize10TimesButtonHeight = learnButtonHeight;

    MyButton copyToEditButton;
    int copyToEditButtonX0 = learnButtonX0;
    int copyToEditButtonY0 = recognize10TimesButtonY0 + recognize10TimesButtonHeight;
    int copyToEditButtonWidth = learnButtonWidth;
    int copyToEditButtonHeight = learnButtonHeight;

    MyButton drawButton;
    int drawButtonX0 = learnButtonX0;
    int drawButtonY0 = copyToEditButtonY0 + copyToEditButtonHeight;
    int drawButtonWidth = learnButtonWidth;
    int drawButtonHeight = learnButtonHeight;

    MyButton ereaseButton;
    int ereaseButtonX0 = learnButtonX0;
    int ereaseButtonY0 = drawButtonY0 + drawButtonHeight;
    int ereaseButtonWidth = learnButtonWidth;
    int ereaseButtonHeight = learnButtonHeight;

    MyButton saveImgButton;
    int saveImgButtonX0 = learnButtonX0;
    int saveImgButtonY0 = ereaseButtonY0 + ereaseButtonHeight;
    int saveImgButtonWidth = learnButtonWidth;
    int saveImgButtonHeight = learnButtonHeight;

    static Network network = new Network();

    static public void main(String args[]) {
        PApplet.main(new String[]{"Main.Sketch"});
    }

    @Override
    public void setup() {

        textSize(25);
        grid = new Cell[cols][rows];
        grid2 = new Cell[cols][rows];
        for (int i = gridX0; i < rows; i++) {
            for (int j = gridY0; j < cols; j++) {
                grid[i][j] = new Cell(i * cellWidth, j * cellHeight, cellWidth, cellHeight, 128);
                grid2[i][j] = new Cell(i * cellWidth + grid2X0, j * cellHeight, cellWidth, cellHeight, 128);
                grid[i][j].display();
                grid2[i][j].display();

            }
        }

        clearButton = new MyButton(clearButtonX0, clearButtonY0, clearButtonHeight, clearButtonWidth, "clear");
        clearButton.display();

        learnButton = new MyButton(learnButtonX0, learnButtonY0, learnButtonHeight, learnButtonWidth, "learn");
        learnButton.display();

        recognizeButton = new MyButton(recognizeButtonX0, recognizeButtonY0, recognizeButtonHeight, recognizeButtonWidth, "answ");
        recognizeButton.display();

        recognize10TimesButton = new MyButton(recognize10TimesButtonX0, recognize10TimesButtonY0, recognize10TimesButtonHeight, recognize10TimesButtonWidth, "answ   x10");
        recognize10TimesButton.display();

        copyToEditButton = new MyButton(copyToEditButtonX0, copyToEditButtonY0, copyToEditButtonHeight, copyToEditButtonWidth, "copy");
        copyToEditButton.display();

        drawButton = new MyButton(drawButtonX0, drawButtonY0, drawButtonHeight, drawButtonWidth, "draw");
        drawButton.display();

        ereaseButton = new MyButton(ereaseButtonX0, ereaseButtonY0, ereaseButtonHeight, ereaseButtonWidth, "erease");
        ereaseButton.display();

        saveImgButton = new MyButton(saveImgButtonX0, saveImgButtonY0, saveImgButtonHeight, saveImgButtonWidth, "saveImg");
        saveImgButton.display();

    }

    @Override
    public void settings() {
        size(1000, 700);  // size always goes first!
//  surface.setResizable(true);
    }

    @Override
    public void draw() {

    }

    @Override
    public void mousePressed() {
        update(mouseX, mouseY);
    }

    @Override
    public void mouseDragged() {
        update(mouseX, mouseY);
    }

    void update(int x, int y) {
        if (btn.isOverRec(gridX0, gridY0, rows * cellWidth, cols * cellHeight, x, y)) {
            switchColorOfCell(x, y);
        } else if (btn.btnClicked(clearButton, x, y)) {
            btn.clear(grid, rows, cols);

        } else if (btn.btnClicked(learnButton, x, y)) {
//            LinearMachines = btn.learn(LinearMachines);
            btn.learn();
            System.out.println("nauczyl sie");
        } else if (btn.btnClicked(recognizeButton, x, y)) {
            btn.clear(grid2, rows, cols);
            grid2 = Calculations.copyGrid(btn.answer(grid), grid2);
//            Printer.printMatrix(grid2);
            drawGrid(grid2);
//            double[] output = btn.answer(LinearMachines, grid);
//            drawGrid(output, grid2);

        } else if (btn.btnClicked(copyToEditButton, x, y)) {
            btn.copyToEdit(grid2, grid);
        } else if (btn.btnClicked(drawButton, mouseX, mouseY)) {
            draw = true;
        } else if (btn.btnClicked(ereaseButton, mouseX, mouseY)) {
            draw = false;
        } else if (btn.btnClicked(saveImgButton, mouseX, mouseY)) {
            btn.save(grid);
        }
    }

    void switchColorOfCell(int x, int y) {
        boolean foundCell = false;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (btn.cellClicked(grid[i][j], x, y)) {
                    grid[i][j].changeColor();
//                    foundCell = true;
//                    break;
                }
            }
//            if (foundCell) {
//                break;
//
//            }
        }
    }

    @Override
    public void keyPressed() {
        if ((int) key > 47 && (int) key < 57) {
            int a = (int) key - 48;
            System.out.println(a);
            btn.clear(grid, rows, cols);
            double[][] tmpGrid = ImageManager.loadImage(a);
            drawGrid(Calculations.matrixToVector(tmpGrid), grid);
        }

//        if (key == "2") {
//            btn.clear(grid2, rows, cols);
//            drawGrid(Calculations.matrixToVector(grid), grid2);
////         btn.copyToEdit(grid, grid2);   
//        }
    }

    public class Cell {

        // A cell object knows about its location in the grid 
        // as well as its size with the variables x,y,w,h
        public int active = -1;
        int activeColor = 0;
        int inactiveColor = 128;
        int x, y;   // x,y location
        int w, h;   // width and height
        double angle; // angle for oscillating brightness

        // Cell Constructor
        Cell(int tempX, int tempY, int tempW, int tempH, double tempAngle) {
            x = tempX;
            y = tempY;
            w = tempW;
            h = tempH;
            angle = tempAngle;
        }

        Cell() {
        }

        protected void setInactive() {
            active = -1;
            display(inactiveColor);
        }

        public void changeColor() {
            if (draw) {
                active = 1;
                display(activeColor);
            } else {
                active = -1;
                display(inactiveColor);
            }
//            if (active == -1) {
//                changeActive();
//                display(activeColor);
//            } else {
//                changeActive();
//                display(inactiveColor);
//            }
        }

        public void changeActive() {
            active = -active;
        }

        // Oscillation means increase angle
        void display() {
            double angleColor;
            if (active == 1) {
                angleColor = activeColor;
            } else {
                angleColor = inactiveColor;
            }
            stroke(255);
            // Color calculated using sine wave
            fill(127 + 127 * sin((float) angleColor));
            rect(x, y, w, h);
        }

        public void display(double angle) {
            stroke(255);
            // Color calculated using sine wave
            fill(127 + 127 * sin((float) angle));
            rect(x, y, w, h);
        }
    }

    public class MyButton {

        int x, y;
        int height, width;
        String text;

        public MyButton(int x, int y, int height, int width, String text) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.text = text;
        }

        public void display() {
            stroke(255);
            fill(204, 102, 0);
            rect(x, y, width, height);
            fill(100, 100, 255);
            text(text, x + width / 4, y + height - height / 4);
        }
    }

    private void drawGrid(double[] input, Cell[][] tmpGrid) {
        for (int i = 0; i < tmpGrid.length; i++) {
            for (int j = 0; j < tmpGrid[0].length; j++) {
                if (input[i * tmpGrid.length + j] == 1) {
                    tmpGrid[i][j].changeActive();
                    tmpGrid[i][j].display();
                }
            }
        }
    }

    private void drawGrid(Cell[][] tmpGrid) {
        for (int i = 0; i < tmpGrid.length; i++) {
            for (int j = 0; j < tmpGrid[0].length; j++) {
                tmpGrid[i][j].display();
            }
        }
    }
}

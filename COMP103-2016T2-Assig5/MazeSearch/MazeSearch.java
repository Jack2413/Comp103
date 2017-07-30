// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:Zhancheng Gan
 * Usercode:
 * ID:300378961
 */

import ecs100.UI;
import java.awt.*;
import java.util.*;

public class MazeSearch {

    public final int CELL_SIZE;
    public final int DELAY = 50;

    private final Map<Cell, Set<Cell>> neighbours = new HashMap<Cell, Set<Cell>>();
    private final int size;
    private final Cell entrance, exit;
    private int count;

    Set<Cell> Visited= new HashSet<Cell>(); // (Yellow path) use for cheaking
    Set<Cell> VisitedAgain = new HashSet<Cell>();// (red path)

    Cell NextCell;// two cells at the starting point
    Color color=Color.YELLOW;// set color yellow

    /**
     * Creates a new maze using MazeGenerator and draws it .
     */
    public MazeSearch(int size) {
        this.size = size;

        if (size > 600 / 20) {
            CELL_SIZE = 600 / size;
        } else {
            CELL_SIZE = 20;
        }

        MazeGenerator generator = new MazeGenerator(size);
        generator.generate(neighbours);

        entrance = generator.getEntrance();
        exit = generator.getExit();

        draw();
    }

    /**
     * Draws the maze. This method should be called once after creating a new maze to draw it to
     * the UI class.
     */
    public void draw() {
        UI.clearGraphics();

        UI.setColor(Color.BLACK);
        UI.fillRect(0, 0, size * CELL_SIZE, size * CELL_SIZE);

        for (Cell from : neighbours.keySet()) {
            for (Cell to : neighbours.get(from)) {
                drawPassage(from, to, Color.WHITE, false);
            }
        }

        drawCell(entrance, Color.GREEN, false);
        drawCell(exit, Color.GREEN, false);

    }

    /**
     * Draws a specific cell. This method will fill the interior of a cell with the specified color.
     * The redraw parameter is passed to the UI class, and if it is true this method will delay
     * returning for a while so that the user has time to see the change.
     */
    public void drawCell(Cell cell, Color color, boolean redraw) {
        UI.setColor(color);

        int x = cell.x * CELL_SIZE;
        int y = cell.y * CELL_SIZE;
        int w = CELL_SIZE;
        int h = CELL_SIZE;

        if (redraw) 
            UI.sleep(DELAY);
    }

    /**
     * Draws a passage between two cells, filling both the cells and the space between them with the
     * specified color. If the redraw parameter is true then this method will delay returning for a while 
     * so that the user has time to see the change.
     */
    public void drawPassage(Cell from, Cell to, Color color, boolean redraw) {
        UI.setColor(color);

        int x1 = Math.min(from.x, to.x);
        int x2 = Math.max(from.x, to.x);
        int y1 = Math.min(from.y, to.y);
        int y2 = Math.max(from.y, to.y);

        int x = x1 * CELL_SIZE;
        int y = y1 * CELL_SIZE;
        int w = (x2 + 1 - x1) * CELL_SIZE;
        int h = (y2 + 1 - y1) * CELL_SIZE;

        UI.fillRect(x + 1, y + 1, w - 2, h - 2);

        if (redraw) 
            UI.sleep(DELAY);
    }

    /**
     * Run the exploration algorithm.
     */
    public void run() {
        int count=0;
        explore(entrance);
    }

    /**
     * Mark the current cell as visited, then recursively explore the cell's neighbouring cells.
     * Before exploring a cell draw a passage between the current cell and the cell you are about to
     * explore in yellow, and after exploring a cell draw the same passage in red.
     * 
     * @return true, if the exit has been found. Otherwise, returns false. 
     */
    private boolean explore(Cell cell) {

        //long start = System.nanoTime();
        //while(true){// until finded the exit

        cell.setVisited(true);
        //find left down
        for(Cell nei:neighbours.get(cell)){
            if((nei.x>cell.x||nei.y>cell.y)&&!nei.isVisited()){// pick the neighour cell which is left down to become Nextcell
                NextCell=nei;
                color=Color.YELLOW;
                Visited.add(cell);// add the cell is been visited
                break;

            } 
        }
        
        //find white path
        if(NextCell==cell){// when the loop above cant finded the right cell (the cell didn't change) use this loop
            for(Cell nei:neighbours.get(cell)){
                if(!nei.isVisited()){// pick the neighour cell which is not visited to become Nextcell

                    NextCell=nei;
                    color=Color.YELLOW;
                    Visited.add(cell);// add the cell is been visited
                    break;
                }
            }
        }
        
        //find red path
        if(NextCell==cell){ // when the loop above cant finded the right cell (the cell didn't change) use this loop
            for(Cell nei:neighbours.get(cell)){       
                if(Visited.contains(nei)&&!VisitedAgain.contains(nei)){// pick the cell that in yellow path but not in red path

                    NextCell=nei;
                    color=Color.RED;
                    VisitedAgain.add(cell); // add the cell is been visiteAgain
                    break;

                }
            }
        }

        UI.sleep(1);

        drawPassage(cell,NextCell,color, false);
        drawCell(cell,color, false);

        if(NextCell==this.exit){
            //long time = System.nanoTime() - start;
            //UI.println("Time used: "+ time/1000000000+"s");
            return true;
        }
        else{
            explore(NextCell);
            return false;
        }
    }

    public static void main(String[] args) {
        while (true) {
            int size = UI.askInt("What size maze would you like?");
            if (size <= 70) {
                MazeSearch ms = new MazeSearch(size);
                ms.run();
            }
            else UI.println("Give a smaller number (max 70).");
        }
    }
}


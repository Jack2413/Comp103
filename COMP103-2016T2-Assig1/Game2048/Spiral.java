import ecs100.*;
import java.util.*;
import java.awt.Color;
/**
 * Write a description of class Spiral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spiral
{
    // instance variables - replace the example below with your own
    private final int rows = 10;
    private final int cols = 10;
    private final int size = 50;
    private  int space = 50;
    private int top = 25;
    private int left = 25;
    private int topN = 50;
    private int leftN = 50;
    private String numSS;

    private int [][] nums  = new int [rows][cols];

    /**
     * Constructor for objects of class Spiral
     */
    public Spiral()
    {
        // initialise instance variables
        UI.addButton("Line", this::Line );
        UI.addButton("Square", this::Square );
        UI.addButton("Spiral", this::Spiral );
        UI.addButton("Clear", UI::clearGraphics );
        UI.addButton("Quit", UI::quit );
        int count=1;
        for(int row=0; row<rows; row++){
            for(int col=0; col<cols; col++){
                nums[row][col]+=count;
                count++;
            }
        }

    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void Line(){

        for(int col = 0; col<cols; col++ ){
            int num = nums[0][col];
            this.numSS = Integer.toString(num);
            UI.setColor(new Color(25*nums[0][col]));
            UI.fillRect(space*(col+1)-25,top,size,size);
            UI.setColor(Color.white);
            UI.drawString (numSS,leftN*(col+1)-5,topN+3);

        }

    }

    public void Square() {

        for(int row = 0; row<rows; row ++){
            int count=1;
            for(int col = 0; col<cols; col++ ){
                int num = nums[row][col];
                this.numSS = Integer.toString(num);
                UI.setColor(new Color((5/2)*nums[row][col]));
                UI.fillRect((space*count)-left,((row+1)*space)-top,size,size);

                UI.setColor(Color.white);
                UI.drawString (numSS,(space*count)-5,((row+1)*space)+3);

                UI.print(nums[row][col]);

                count++;

            }
        }

    }

    public void Spiral(){
        int number=1;
        int value=0;

        for(int count = 9; 0<count;count-=2){
            
            UI.print(count);
            for(int t=0; t<count;t++ ){
                
                numSS = Integer.toString(number);
                UI.setColor(new Color((5/2)*number));

                UI.fillRect(left+space*t+(value*space),top+(value*space),size,size);
                UI.setColor(Color.white);
                UI.drawString (numSS,left+space*t+(value*space)+20,top+(value*space)+25);
                
                number++;

            }
            for(int l=0; l<count; l++ ){
                
                numSS = Integer.toString(number);
                UI.setColor(new Color((5/2)*number));

                UI.fillRect(left+space*count+(value*space),top+space*l+(value*space),size,size);
                UI.setColor(Color.white);
                UI.drawString (numSS,left+space*count+(value*space)+20,top+space*l+(value*space)+25);
                
                number++;
            }

            for (int b=0; b<count; b++){
                
                numSS = Integer.toString(number);
                UI.setColor(new Color((5/2)*number));

                UI.fillRect(left+space*count-b*space+(value*space),top+space*count+(value*space),size,size);
                UI.setColor(Color.white);
                UI.drawString (numSS,left+space*count-b*space+(value*space)+20,top+space*count+(value*space)+25);
                
                number++;
            }

            for(int r=0; r<count; r++){
                
                numSS = Integer.toString(number);
                UI.setColor(new Color((5/2)*number));

                UI.fillRect(left+(value*space),top+space*count-r*space+(value*space),size,size);
                UI.setColor(Color.white);
                UI.drawString (numSS,left+(value*space)+20,top+space*count-r*space+(value*space)+25);
                
                number++;
            }
            value++;
            
        }

    }
}


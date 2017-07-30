package ENGR110TUT;

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 * Write a description of class ENGR110tut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ENGR110tut
{
    // instance variables - replace the example below with your own
    private int x;
    private double Capacity;
    private double ElectricityGrid;
    private double gridLosses;
    private double CostperMWh;

    

            /**
             * Constructor for objects of class ENGR110tut
             */
    public ENGR110tut()
    {
        UI.addButton("Calculate",()->Calculate());
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void Calculate()
    {
        Capacity=UI.askDouble("Enter Capacity(MW):");
        CostperMWh=UI.askDouble("Cost per MWh:");
        gridLosses=UI.askDouble("grid losses(%):");
        double totalcost = Capacity*CostperMWh*gridLosses/100;
        UI.println("Total cost:" + totalcost);

    }
}

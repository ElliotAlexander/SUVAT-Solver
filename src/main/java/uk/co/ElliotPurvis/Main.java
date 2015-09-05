package uk.co.ElliotPurvis;

import uk.co.ElliotPurvis.equations.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    MainWindow mainWindow;

    private ArrayList<Equation> registeredEquations;
    private HashMap<String, Double> values;


    public static void main(String[] args) {
        Main mainClass = new Main();
        mainClass.setup();

    }

    /**
     * @param window
     * @return @param
     * Defines default settings for a new window.
     */
    private Window setupDefaultWindow(JFrame window){
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // Define size of the display we're running on
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // Centre the window on the display
        window.setLocation((int) dim.getWidth() / 2 - (window.getWidth() / 2), (int) dim.getHeight() / 2 - (window.getHeight() / 2));
        window.setVisible(true);
        return window;
    }


    /**
     * Non static main
     *
     */
    private void setup() {

        values = new HashMap<String, Double>();
        values.put("V", null);
        values.put("A", null);
        values.put("S", null);
        values.put("U", null);
        values.put("T", null);

        registeredEquations = new ArrayList<Equation>();
        registeredEquations.add(new Equation1());
        registeredEquations.add(new Equation2());
        registeredEquations.add(new Equation3());
        registeredEquations.add(new Equation4());
        registeredEquations.add(new Equation5());



        mainWindow = new MainWindow(this);
        setupDefaultWindow(mainWindow);

    }


    /**
     * Use the values defined in HashMap to decide which equation is relevant
     */
    public void calculate() {

        // Stores the keys to reference the null values in HashMap<Double> values;
        ArrayList<String> nullvalues = new ArrayList<String>();


        for(String tempKey : values.keySet()){
            if(values.get(tempKey)==null){
                nullvalues.add(tempKey);
                System.out.print("Found null value " + tempKey + ", adding to array. \n");
            }
        }

        System.out.print("Beginning loop of nullvalues, of size " + nullvalues.size() + "\n");

        nullValueLoop:
        for(String nullvalue : nullvalues){



            System.out.print("Starting outerloop, iterating nullvalue " + nullvalue + "\n");

            EquationLoop:
            for(Equation e : registeredEquations){

                // Flag to keep ` of if we have any unknowns yet
                boolean foundUnknown = false;

                // Iterate through the values required
                // If the loop does not exit, the equation only requires one of our null values and the program continues.
                for(String s : e.getRequiredChars()){

                    // If we have a null value and havent yet had one, all is good
                    if(nullvalues.contains(s) && foundUnknown == false){
                        foundUnknown = true;

                    // We have more than one null value, abort
                    } else if(foundUnknown == true && nullvalues.contains(s)){
                        continue EquationLoop;
                    }
                }


                System.out.print("Using equation " + e.getIdentifier() + "\n");

                System.out.print("Found suitable equation.\n");

                Double returnValue = e.calculate(values, nullvalue);

                System.out.print("Calculated return value.\n");

                values.put(nullvalue, returnValue);

                nullvalues.remove(nullvalue);

                System.out.print("Added new value to values array\n");
                System.out.print("Calculated value  " + nullvalue + " to " + values.get(nullvalue) + "\n");
                break EquationLoop;
            }
            System.out.print("Ending outerloop iteration \n");
        }
        mainWindow.updateTextBoxes();

    }

    // We use an interface to keep the equations together and easily iterate through multiple classes, as well as for readabillity.
    public interface Equation {
        public String[] getRequiredChars();

        public Double calculate(HashMap<String, Double> passedValues, String nullValue);

        public String getIdentifier();
    }

    public void setValue(String s, Double x){
        values.put(s, x);
    }

    public Double getValue(String s){
        return values.get(s);
    }
}

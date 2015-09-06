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

    private Main main;

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

        main = this;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainWindow = new MainWindow(main);
                setupDefaultWindow(mainWindow);
            }
        });
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

        if(nullvalues.size() > 2){



        }

        nullValueLoop:
        for(String nullValueKey : nullvalues){

            System.out.print("Starting outerloop, iterating nullvalue " + nullValueKey + "\n");

            EquationLoop:
            for(Equation e : registeredEquations){



                // Flag to keep ` of if we have any unknowns yet
                boolean foundUnknown = false;

                // Iterate through the values required
                // If the loop does not exit, the equation only requires one of our null values and the program continues.
                boolean containsUnknown = false;
                for(String s : e.getRequiredChars()){

                    if(s == nullValueKey){
                        containsUnknown = true;
                    }

                    // If we have a null value and havent yet had one, all is good
                    if(nullvalues.contains(s) && foundUnknown == false && values.get(s)==null){
                        foundUnknown = true;

                    // We have more than one null value, abort
                    } else if(foundUnknown == true && nullvalues.contains(s) && values.get(s)==null){
                        continue EquationLoop;
                    }
                }

                if(containsUnknown == false){
                    continue EquationLoop;
                }


                System.out.print("Using equation " + e.getIdentifier() + "\n");

                Double returnValue = e.calculate(values, nullValueKey);


                values.put(nullValueKey, returnValue);


                System.out.print("Calculated value  " + nullValueKey + " to " + values.get(nullValueKey) + "\n");
                break EquationLoop;
            }
            continue nullValueLoop;
        }
    }

    // We use an interface to keep the equations together and easily iterate through multiple classes, as well as for readabillity.
    public interface Equation {
        String[] getRequiredChars();

        Double calculate(HashMap<String, Double> passedValues, String nullValue);

        String getIdentifier();
    }

    public void setValue(String s, Double x){
        values.put(s, x);
    }

    public Double getValue(String s){
        return values.get(s);
    }
}

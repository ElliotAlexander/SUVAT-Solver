package uk.co.ElliotPurvis;

import uk.co.ElliotPurvis.JFrame.ErrorWindow;
import uk.co.ElliotPurvis.JFrame.MainWindow;
import uk.co.ElliotPurvis.equations.*;
import uk.co.ElliotPurvis.exceptions.InsufficientValuesException;

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

    public void newErrorWindow(String title, String errorMessage){
        ErrorWindow errorWindow = new ErrorWindow(title,errorMessage);
        setupDefaultWindow(errorWindow);
        // Override defaults set in setupDefaultWindow, we want to close only this window on exit.
        errorWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }


    /**
     * Use the values defined in HashMap to decide which equation is relevant
     */
    public void calculate() throws InsufficientValuesException {

        // Stores the keys to reference the null values in HashMap<Double> values;
        ArrayList<String> nullvalues = new ArrayList<String>();



        for(String tempKey : values.keySet()){
            if(values.get(tempKey)==null){
                nullvalues.add(tempKey);
            }
        }

        if(nullvalues.size()>2){
            newErrorWindow("Too many values!", "You've entered too many values to calculate. Please enter a maximum of two unknown values.");
            throw new InsufficientValuesException();
        }


        nullValueLoop:
        for(String nullValueKey : nullvalues){

            EquationLoop:
            for(Equation e : registeredEquations){



                // Flag to keep ` of if we have any unknowns yet
                boolean foundUnknown = false;

                // Iterate through the values required
                // If the loop does not exit, the equation only requires one of our null values and the program continues.
                boolean containsUnknown = false;
                for(String s : e.getRequiredChars()){

                    containsUnknown = s==nullValueKey ? true : false;

                    // If we have a null value and havent yet had one, all is good
                    if(nullvalues.contains(s) && foundUnknown == false && values.get(s)==null){
                        foundUnknown = true;

                    // We have more than one null value, abort
                    } else if(foundUnknown && nullvalues.contains(s) && values.get(s)==null){
                        continue EquationLoop;
                    }
                }

                if(containsUnknown == false){
                    continue EquationLoop;
                }


                Double returnValue = e.calculate(values, nullValueKey);


                values.put(nullValueKey, returnValue);

                break EquationLoop;
            }
            continue nullValueLoop;
        }
    }

    // We use an interface to keep the equations together and easily iterate through multiple classes, as well as for readabillity.
    public interface Equation {
        String[] getRequiredChars();

        Double calculate(HashMap<String, Double> passedValues, String nullValue);

    }

    public void setValue(String s, Double x){
        values.put(s, x);
    }

    public Double getValue(String s){
        return values.get(s);
    }
}

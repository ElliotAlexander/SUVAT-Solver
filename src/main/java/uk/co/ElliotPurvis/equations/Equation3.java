package uk.co.ElliotPurvis.equations;

import uk.co.ElliotPurvis.Main;

import java.util.HashMap;

/**
 * Created by Elliot on 04/09/2015.
 */
public class Equation3 implements Main.Equation {


    /**
     *  s = ((u+v)/2 )* t
     **/


    public String[] getRequiredChars() {
        return new String[]{"V", "U", "S", "T"};
    }

    public Double calculate(HashMap<String, Double> passedValues, String nullValue) {

        if(nullValue == "V") {
            return ((2 * passedValues.get("S")) / passedValues.get("T")) - passedValues.get("U");
        } else if(nullValue == "U"){
            return ((2 * passedValues.get("S")) / passedValues.get("T")) - passedValues.get("V");
        } else if(nullValue == "S") {
            return ((passedValues.get("U") + passedValues.get("V")) / 2) * passedValues.get("T");
        } else if(nullValue == "T") {
            return (passedValues.get("S") / (0.5 * (passedValues.get("U") + passedValues.get("V"))));
        }
        return null;
    }
}

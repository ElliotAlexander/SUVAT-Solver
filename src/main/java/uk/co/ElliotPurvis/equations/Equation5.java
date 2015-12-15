package uk.co.ElliotPurvis.equations;

import uk.co.ElliotPurvis.Main;

import java.util.HashMap;

/**
 * Created by Elliot on 04/09/2015.
 */
public class Equation5 implements Main.Equation {

    /**
     *  S = vt - 1/2at^2
     **/

    public String[] getRequiredChars() {
        return new String[]{"V", "T", "S", "A"};
    }



    public Double calculate(HashMap<String, Double> passedValues, String nullValue) {

        if(nullValue == "V") {
            return (passedValues.get("S") + (0.5 * passedValues.get("A") * (passedValues.get("T") * passedValues.get("T")))) / passedValues.get("T") ;
        } else if(nullValue == "T"){
            /**
             *  Rearranged to : (V +- sqrt(V^2 - 2AS)) / a
             *
             * We can't  accept a negative time
             */
            double tempValue;

            tempValue = (passedValues.get("V") + Math.sqrt((passedValues.get("V") * passedValues.get("V")) - (2 * passedValues.get("A") * passedValues.get("S"))))/passedValues.get("A");

            if(tempValue < 0){
                tempValue = (passedValues.get("V") - Math.sqrt((passedValues.get("V") * passedValues.get("V")) - (2 * passedValues.get("A") * passedValues.get("S"))))/passedValues.get("A");
            }

            return tempValue;
        } else if(nullValue == "S") {
            return (passedValues.get("V") * passedValues.get("T")) - (0.5 * passedValues.get("A") * (passedValues.get("T") * passedValues.get("T")));
        } else if(nullValue == "A") {
            return ((passedValues.get("T") * passedValues.get("T")) - passedValues.get("S") ) / (0.5 * (passedValues.get("T") * passedValues.get("V") ));
        }
        return null;
    }
}

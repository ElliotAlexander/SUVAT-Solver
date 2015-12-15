package uk.co.ElliotPurvis.equations;

import uk.co.ElliotPurvis.Main;

import java.util.HashMap;

/**
 * Created by Elliot on 04/09/2015.
 */
public class Equation2 implements Main.Equation {

    /**
     * S = UT + 1/2AT^2
     *
     * @return
     */





    public String[] getRequiredChars() {
        return new String[]{"S", "U", "A", "T"};
    }

    public Double calculate(HashMap<String, Double> passedValues, String nullValue) {



        if(nullValue == "S") {
            return ((passedValues.get("U") * passedValues.get("T")) + (0.5 * passedValues.get("A") * (passedValues.get("T") * passedValues.get("T"))));
        } else if(nullValue == "U"){
            return (passedValues.get("S") - (0.5 * passedValues.get("S") * (passedValues.get("T")*passedValues.get("T")))) / passedValues.get("T");
        } else if(nullValue == "A") {
            return (2 * (passedValues.get("S") - (passedValues.get("S") * passedValues.get("T") )) / (passedValues.get("T") * passedValues.get("T")));
        } else if(nullValue == "T") {
            /**
             *  Rearranged to : (-u +- sqrt(2SA + U^2)) / a
             *
             * We can't  accept a negative time
             */
            double tempValue;

            tempValue = (-passedValues.get("U") + Math.sqrt((passedValues.get("U") * passedValues.get("U")) + (2 * passedValues.get("S")))) / passedValues.get("A");

            if(tempValue < 0){
                tempValue = (-passedValues.get("U") - Math.sqrt((passedValues.get("U") * passedValues.get("U")) + (2 * passedValues.get("S")))) / passedValues.get("A");
            }

            return tempValue;
        }
        return null;
    }
}

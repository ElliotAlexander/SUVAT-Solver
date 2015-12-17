package uk.co.ElliotPurvis.equations;

import uk.co.ElliotPurvis.Main;

import java.util.HashMap;

/**
 * Created by Elliot on 04/09/2015.
 */
public class Equation4 implements Main.Equation {
    /**
     *  V^2 = U^2 + 2as
     **/

    public String[] getRequiredChars() {
        return new String[]{"V", "U", "S", "A"};
    }

    public Double calculate(HashMap<String, Double> passedValues, String nullValue) {
        if(nullValue == "V") {
            return Math.sqrt((passedValues.get("U")*passedValues.get("U")) + (2 * passedValues.get("A") * passedValues.get("S")) );
        } else if(nullValue == "U"){
            return Math.sqrt((passedValues.get("V") * passedValues.get("V")) + (2 * passedValues.get("A") * passedValues.get("S")));
        } else if(nullValue == "S") {
            return ((passedValues.get("V") * passedValues.get("V")) - (passedValues.get("U") * passedValues.get("U"))) / (2 * passedValues.get("A"));
        } else if(nullValue == "A") {
            return ((passedValues.get("V") * passedValues.get("V")) - (passedValues.get("U") * passedValues.get("U"))) / (2 * passedValues.get("S"));
        }
        return null;
    }
}

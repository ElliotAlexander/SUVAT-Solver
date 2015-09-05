package uk.co.ElliotPurvis.equations;


import uk.co.ElliotPurvis.Main;

import java.util.HashMap;

/**
 * Created by Elliot on 19/08/2015.
 */
public class Equation1 implements Main.Equation {

    /**
     *  V = U + AT
     **/

    public String getIdentifier(){
        return "1";
    }

    public String[] getRequiredChars() {
        return new String[]{"V", "U", "A", "T"};
    }

    public Double calculate(HashMap<String, Double> passedValues, String nullValue) {


       if(nullValue == "V") {
            return (passedValues.get("U") + (passedValues.get("A")*passedValues.get("T")));
        } else if(nullValue == "U"){
            return (passedValues.get("V") - (passedValues.get("A") * passedValues.get("T")));
        } else if(nullValue == "A") {
            return (passedValues.get("V") - passedValues.get("U") ) / passedValues.get("T");
        } else if(nullValue == "T") {
            return (passedValues.get("V") - passedValues.get("U") ) / passedValues.get("A");
        }
        return null;
    }
}

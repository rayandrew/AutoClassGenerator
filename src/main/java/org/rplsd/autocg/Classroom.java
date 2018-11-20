package org.rplsd.autocg;

import java.util.HashMap;

public class Classroom {
    private String number;
    private HashMap<String, Integer> facilities;

    public Classroom(String number, HashMap<String, Integer> facilities) {
        this.number = number;
        this.facilities = facilities;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public HashMap<String, Integer> getFacilities() {
        return facilities;
    }

    public void setFacilities(HashMap<String, Integer> facilities) {
        this.facilities = facilities;
    }

}
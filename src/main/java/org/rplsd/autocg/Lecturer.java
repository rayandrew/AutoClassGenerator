package org.rplsd.autocg;

import java.util.ArrayList;

public class Lecturer {
    private String name;
    private ArrayList<ArrayList<Boolean>> availability;

    public Lecturer(String name, ArrayList<ArrayList<Boolean>> availability) {
      this.name = name;
      this.availability = availability;
    }

    public ArrayList<ArrayList<Boolean>> getAvailability() {
        return availability;
    }

    public void setAvailability(ArrayList<ArrayList<Boolean>> availability) {
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
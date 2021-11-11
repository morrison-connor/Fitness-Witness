// Class for each unique exercise to be added to a workout

package com.example.fitnesswitness;

public class Exercise {

    // Attributes
    private String Name;
    private Boolean isTimed;
    private int duration; // in time or reps, 1 sec = 1 rep

    // Constructor
    public Exercise (String str, Boolean bool, int num) {
        Name = str;
        isTimed = bool;
        duration = num;
    }

    public void setName (String string) {
        Name = string;
    }

    public String getName () {
        return Name;
    }

    public void setIsTimed (Boolean bool) {
        isTimed = bool;
    }

    public boolean getIsTimed () {
        return isTimed;
    }

    public void setDuration(int num) {
        duration = num;
    }

    public int getDuration() {
        return duration;
    }
}

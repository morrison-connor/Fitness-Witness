package com.example.fitnesswitness;

import android.util.Log;

import java.util.ArrayList;

public class Routine {

    // Attributes
    ArrayList<Exercise> Routine;
    private String TAG = "CM-Routine";

    // Constructor
    public Routine() {
        Log.d(TAG, "Workout Routine Constructor called.");
        Routine = new ArrayList<Exercise>();

    }

    public ArrayList<Exercise> getRoutine() {
        return Routine;
    }

    public void clearRoutine() {
        Routine.clear();
    }

    public void setDefaultHIITRoutine() {
        Log.d(TAG, "To set default HIIT routine");
        addRest();
        Routine.add(new Exercise("Jumping Jacks", true, 30 ));
        addRest();
        Routine.add(new Exercise("Wall Sit", true, 30 ));
        addRest();
        Routine.add(new Exercise("Push Ups", true, 30 ));
        addRest();
        Routine.add(new Exercise("Crunches", true, 30 ));
        addRest();
        Routine.add(new Exercise("Butt Bridges", true, 30 ));
        addRest();
        Routine.add(new Exercise("Squats", true, 30 ));
        addRest();
        Routine.add(new Exercise("Triceps Dips", true, 30 ));
        addRest();
        Routine.add(new Exercise("Plank", true, 30 ));
        addRest();
        Routine.add(new Exercise("High Knees", true, 30 ));
        addRest();
        Routine.add(new Exercise("Lunges", true, 30 ));
        addRest();
        Routine.add(new Exercise("Push Up and Rotation", true, 30 ));
        addRest();
        Routine.add(new Exercise("Right Plank", true, 30 ));
        addRest();
        Routine.add(new Exercise("Left Plank", true, 30 ));
    }

    public void setMyYogaRoutine() {
        Routine.add(new Exercise("Rest", true, 10 ));
        Routine.add(new Exercise("Deep Breathing", true, 40 ));
        Routine.add(new Exercise("Shoulder and Neck stretch", true, 40 ));
        Routine.add(new Exercise("Cat and Cow", true, 40 ));
        Routine.add(new Exercise("Thread the Needle (Left)", true, 40 ));
        Routine.add(new Exercise("Thread the Needle (Right)", true, 40 ));
        Routine.add(new Exercise("Downward Dog", true, 40 ));
        Routine.add(new Exercise("Forward Fold", true, 40 ));
        Routine.add(new Exercise("Mountain Pose", true, 40 ));
        Routine.add(new Exercise("Yogi Squat", true, 40 ));
        Routine.add(new Exercise("Forearm Plank", true, 20 ));
        Routine.add(new Exercise("Baby Cobra", true, 20 ));
        Routine.add(new Exercise("Child's Pose", true, 80 ));

    }

    public void setCustomRoutine () {
        addRest();
        Routine.add(new Exercise("Push ups", false, 10 ));
        addRest();
        Routine.add(new Exercise("Crunches", false, 30 ));
        Routine.add(new Exercise("Jumping Jacks", true, 4));
    }

    public String getExerciseName (int index) {
        return Routine.get(index).getName();
    }

    public int getExerciseDuration (int index) {
        return Routine.get(index).getDuration();
    }

    public boolean getExerciseIsTimed (int index) {
        return Routine.get(index).getIsTimed();
    }

    public void addRest () {
        Routine.add(new Exercise("Rest", true, 10));
    }

    public int getExerciseLength() {
        return Routine.size();
    }

    public void setRoutine2() {
        Routine.add(new Exercise("Ready to Go", true, 10));
        Routine.add(new Exercise("Jumping Jacks", false, 20 ));
        Routine.add(new Exercise("Rest", true, 10));
        Routine.add(new Exercise("Push Ups", false, 20 ));
        Routine.add(new Exercise("Rest", true, 10));
        Routine.add(new Exercise("Crunches", false, 20));
        Routine.add(new Exercise("Rest", true, 10));
        Routine.add(new Exercise("Squats", false, 20));
    }
}

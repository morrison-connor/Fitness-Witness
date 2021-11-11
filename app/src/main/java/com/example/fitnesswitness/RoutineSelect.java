package com.example.fitnesswitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RoutineSelect extends AppCompatActivity { // RoutineSelect.java

    // widgets
    ConstraintLayout RoutineConstraintLayout;
    LinearLayout RoutineSelectLayout;
    TextView Routine1TV;
    TextView Routine2TV;
    TextView Routine3TV;
    TextView BackTV;

    boolean goEdit;

    private String TAG = "CM-RoutineSelect";

    Context mContext; // lowercase m for global variables

    String routineType;
    boolean isWorkout; // TODO: Is this necessary?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_select);
        mContext = this;
        RoutineSelectLayout = findViewById(R.id.WS_LinearLayout);
        Routine1TV = findViewById(R.id.RS_R1);
        Routine2TV = findViewById(R.id.RS_R2);
        Routine3TV = findViewById(R.id.RS_R3);
        BackTV = findViewById(R.id.WS_BackTV);
        RoutineConstraintLayout = findViewById(R.id.WS_ConstraintLayout);
        View background = this.getWindow().getDecorView();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            routineType = extras.getString("Routine Type");
            Log.d(TAG, String.valueOf(routineType));
            goEdit = extras.getBoolean("Go Edit");
        }

        switch(routineType) {
            case "Workout":
                Log.d(TAG, "Workout Routine Selected.");
                isWorkout = true;
                RoutineConstraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.ExerciseYellow));
                Routine1TV.setText("Workout Routine 1");
                Routine2TV.setText("Workout Routine 2");
                Routine3TV.setText("Workout Routine 3");

                /* TODO: implemented once there is a variable # of routine selections
                for (int i = 0; i < 4; i++) { // what if there was a way we could put the textviews in a list/array?

                }

                 */

                break;
            case "Yoga":
                Log.d(TAG, "Yoga Routine Selected.");
                isWorkout = false;
                RoutineConstraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.YogaBlue));
                Routine1TV.setText("Yoga Routine 1");
                Routine2TV.setText("Yoga Routine 2");
                Routine3TV.setText("Yoga Routine 3");
                break;
        }

        if (goEdit) {
            RoutineConstraintLayout.setBackgroundColor(mContext.getResources().getColor(R.color.SettingsGrey));
        }

        Routine1TV.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startWorkout(routineType, 1); // 'magic number' 1,2,3 for these onclicks, can be cleaned up with a for loop
            }
        });

        Routine2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkout(routineType, 2); // 'magic number' 1,2,3 for these onclicks, can be cleaned up with a for loop
            }
        });

        Routine3TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWorkout(routineType, 3); // 'magic number' 1,2,3 for these onclicks, can be cleaned up with a for loop
            }
        });

        BackTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startWorkout(String routineType, int routineSelect) {
        if (goEdit) {
            Log.d(TAG, "Edit Routine " + String.valueOf(routineSelect));
            Intent RD_Intent = new Intent(mContext, RoutineEdit.class);
            startActivity(RD_Intent);
        } else {
            Log.d(TAG, "Start Routine " + String.valueOf(routineSelect));
            Intent RD_Intent = new Intent(mContext, RoutineDisplay.class);
            RD_Intent.putExtra("Routine Type", routineType);
            RD_Intent.putExtra("Routine Selection", routineSelect);
            startActivity(RD_Intent);
        }
    }
}
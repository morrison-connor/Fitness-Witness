package com.example.fitnesswitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // widgets
    ConstraintLayout mainLayout;
    TextView CWMenuText;
    TextView YogaMenuText;
    TextView SettingsMenuText;

    //vars
    Context mContext; // lowercase m for global variables

    private String TAG = "CM-Mainpage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started the app !");
        mContext = this;

        mainLayout = findViewById(R.id.start_clMain);
        CWMenuText = findViewById(R.id.start_WorkoutTV);
        YogaMenuText = findViewById(R.id.start_YogaTV);
        SettingsMenuText = findViewById(R.id.start_SettingsTV);

        CWMenuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "goToList called.");
                goToWorkoutSelect();
            }
        });

        YogaMenuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "goToList called.");
                goToYogaSelect();
            }
        });

        SettingsMenuText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "goToList called.");
                goToSettings();
            }
        });
    }

    private void goToWorkoutSelect() {
        Log.d(TAG, "goToList called!");
        Intent WSIntent = new Intent(mContext, RoutineSelect.class);
        WSIntent.putExtra("Routine Type", "Workout");
        WSIntent.putExtra("Go Edit", false);
        startActivity(WSIntent);
    }

    private void goToYogaSelect() {
        Log.d(TAG, "goToYoga called!");
        Intent WSIntent = new Intent(mContext, RoutineSelect.class);
        WSIntent.putExtra("Routine Type", "Yoga");
        WSIntent.putExtra("Go Edit", false);
        startActivity(WSIntent);

    }

    private void goToSettings() {
        Log.d(TAG, "goToSettings called!"); // name it Settings or SettingsSelect?
        Intent WSIntent = new Intent(mContext, Settings.class);
        startActivity(WSIntent);
    }
}
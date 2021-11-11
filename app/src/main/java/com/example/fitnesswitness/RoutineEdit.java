package com.example.fitnesswitness;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoutineEdit extends AppCompatActivity {

    // widgets
    TextView BackTV;
    Button exitButton;

    private String TAG = "CM-RoutineEdit";

    Context mContext; // lowercase m for global variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_edit);
        mContext = this;

        exitButton = findViewById(R.id.RE_Exit);
    }

    public void XButtonClick(View v) {
        finish();
    }
}

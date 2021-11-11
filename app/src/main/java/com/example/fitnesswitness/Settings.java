package com.example.fitnesswitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private String TAG = "CM-Settings";
    LinearLayout RoutineSelectLayout;
    TextView SettingsLayout;
    TextView EditWorkoutTV;
    TextView EditYogaTV;
    TextView BackTV;
    Spinner voiceSelect;

    Context mContext; // lowercase m for global variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mContext = this;
        // SettingsLayout = findViewById(R.id.SE_LinearLayout);
        EditWorkoutTV = findViewById(R.id.SE_EWR);
        EditYogaTV = findViewById(R.id.SE_EYR);
        BackTV = findViewById(R.id.SE_BackTV);
        voiceSelect = findViewById(R.id.SET_VoiceSpinner);

        //voiceSelect.setOnItemClickListener((AdapterView.OnItemClickListener) this); // What does this do?

        String[] items = new String[]{"Amy", "Brian", "Joanna", "Joey"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        voiceSelect.setAdapter(adapter);
        /* TODO: Fix spinner code

        Spinner spinner = (Spinner) findViewById(R.id.SET_VoiceSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, // Create an ArrayAdapter using the string array and a default spinner layout
                R.array.voices_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        spinner.setAdapter(adapter); // Apply the adapter to the spinner


    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            String text = (String) parent.getItemAtPosition(pos);
            Log.d(TAG, text);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    public void doneButtonClick(View v) {
        finish();
    }


 */

        EditWorkoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Custom workout start");
                editWorkout("Workout");
            }
        });

        EditYogaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Custom workout start");
                editWorkout("Yoga");
            }
        });

    BackTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // TODO: Performing action onItemSelected and onNothing selected for the spinner
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        //Toast.makeText(getApplicationContext(),country[position] , Toast.LENGTH_LONG).show();
        Log.d(TAG, position + " selected."); // not working
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // Nothing
    }

    private void editWorkout(String name) {
        Intent WSIntent = new Intent(mContext, RoutineSelect.class);
        WSIntent.putExtra("Routine Type", name);
        WSIntent.putExtra("Go Edit", true);
        startActivity(WSIntent);
    }
}
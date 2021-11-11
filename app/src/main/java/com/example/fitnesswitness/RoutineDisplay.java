package com.example.fitnesswitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class RoutineDisplay extends AppCompatActivity {

    ConstraintLayout layout;
    Chronometer myTimer;
    Button pauseButton;
    Button exitButton;
    TextView topWorkoutTV;
    TextView bottomWorkoutTV;

    Context mContext; // lowercase m for global variables

    Routine myRoutine = new Routine();
    boolean timerOver;
    boolean halfwayDone;
    boolean countdownDone;
    boolean readyForNext = false;
    boolean onRepExercise;
    boolean threadRunning = false;

    private AtomicBoolean running = new AtomicBoolean(false);

    int exerciseIndex = 0;
    int nextDuration;
    int msBuffer = 499;

    String routineType;
    int routineSelection; // TODO: does this work as a string?

    // MediaPlayer ding = MediaPlayer.create(RoutineDisplay.this,R.raw.ding);

    private long pauseOffset;
    private Boolean isPaused;
    private String TAG = "CM-RoutineDisplay";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Log.d(TAG, "Save instance state");
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is killed and restarted.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Log.d(TAG, "Restore instance state");
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
    }

    @Override
    protected void onStop() {
        super.onStop();
        isPaused = false;
        pauseTimer();
        Log.d(TAG, "STOPPED");
        Log.d(TAG, "Thread Interrupt State:" + String.valueOf(timeThread.isInterrupted()));
        Log.d(TAG, "Thread Running State:" + String.valueOf(threadRunning));
        Log.d(TAG, "Pause State:" + String.valueOf(isPaused));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "CREATED");

        mContext = this;
        boolean startLoop = true;
        boolean mainLoop = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_display);

        topWorkoutTV = findViewById(R.id.WD_TopWorkoutTV);
        bottomWorkoutTV = findViewById(R.id.WD_BottomWorkoutTV);
        layout = findViewById(R.id.WD_Layout);

        isPaused = false;
        pauseButton = findViewById(R.id.WD_PauseButton);
        exitButton = findViewById(R.id.WD_Exit);
        myTimer = findViewById(R.id.chronometer);
        myTimer.setBase(SystemClock.elapsedRealtime() + 3000);
        myTimer.start();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            routineType = extras.getString("Routine Type");
            routineSelection = extras.getInt("Routine Selection");
        }

        switch (routineType) {
            case "Workout":
                layout.setBackgroundColor(mContext.getResources().getColor(R.color.ExerciseYellow));
                switch (routineSelection) {
                    case 1:
                        myRoutine.setDefaultHIITRoutine();
                        break;
                    case 2:
                        myRoutine.setRoutine2();
                        break;
                    case 3:
                        break;
                }
                break;
            case "Yoga":
                layout.setBackgroundColor(mContext.getResources().getColor(R.color.YogaBlue));
                switch (routineSelection) {
                    case 1:
                        myRoutine.setMyYogaRoutine();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "RESUMED");
        super.onResume();
        if (!threadRunning) {
            timeThread.start();
            threadRunning = true;
        }
        Log.d(TAG, "Thread Interrupt State: " + String.valueOf(timeThread.isInterrupted()));
        Log.d(TAG, "Thread Running State: " + String.valueOf(threadRunning));
    }

    Thread timeThread = new Thread() {
        public void run() {
            Log.d(TAG, "Start of timeThread");
            MediaPlayer readyToGo = MediaPlayer.create(RoutineDisplay.this,R.raw.brianready);
            readyToGo.start();
            while (exerciseIndex < myRoutine.getExerciseLength()) {
                isPaused = false;
                halfwayDone = false;
                countdownDone = false;
                // TO DO: exercise +1 when screen button is tapped
                if (myRoutine.getExerciseIsTimed(exerciseIndex)) {
                    runOnUiThread(new Runnable() { // This section is looping way too fast - every iteration of the bottom code this happens twice
                        @Override
                        public void run() { // Updates the UI
                            if (!isPaused) {
                                topWorkoutTV.setText(myRoutine.getExerciseName(exerciseIndex));
                                try {
                                    bottomWorkoutTV.setText(myRoutine.getExerciseDuration(exerciseIndex + 1) + " seconds " + myRoutine.getExerciseName(exerciseIndex + 1));
                                } catch (Exception e) {
                                    bottomWorkoutTV.setText("Finish!");
                                }
                                myTimer.setBase(SystemClock.elapsedRealtime() + msBuffer + 1000 * myRoutine.getExerciseDuration(exerciseIndex));
                                myTimer.setVisibility(View.VISIBLE);
                            }
                        }
                    });

                    long startTimeLeft;
                    long currentTimeLeft;
                    // long timeLeft = myTimer.getBase() - SystemClock.elapsedRealtime();
                    // This needs to be the same as what is updating the UI
                    startTimeLeft = SystemClock.elapsedRealtime() + msBuffer + 1000 * myRoutine.getExerciseDuration(exerciseIndex);
                    currentTimeLeft = startTimeLeft;
                    //Log.d(TAG, "Elapsed time started at:"  + String.valueOf(SystemClock.elapsedRealtime()));
                    //Log.d(TAG, "Time left started at:"  + String.valueOf(currentTimeLeft));
                    while (currentTimeLeft > msBuffer ) {  // keeps this thread busy
                        if (timeThread.isInterrupted()) {
                            Log.d(TAG, "We've been interrupted! Let's get outta here.");
                            return; // We've been interrupted! Let's get outta here.
                        }
                        if (isPaused) {
                            startTimeLeft = currentTimeLeft + SystemClock.elapsedRealtime();
                        } else {
                            currentTimeLeft = startTimeLeft -  SystemClock.elapsedRealtime();
                            if ((currentTimeLeft < (msBuffer + 500 * myRoutine.getExerciseDuration(exerciseIndex))) && !halfwayDone && (myRoutine.getExerciseName(exerciseIndex) != "Rest")) {
                                MediaPlayer halfwayAudio = MediaPlayer.create(RoutineDisplay.this,R.raw.brianhalfway);
                                halfwayAudio.start();
                                halfwayDone = true;
                            } else if (currentTimeLeft < (msBuffer + 3000) && !countdownDone) {
                                MediaPlayer countdownAudio = MediaPlayer.create(RoutineDisplay.this,R.raw.briancountdown);
                                countdownAudio.start();
                                countdownDone = true;
                            }
                        }
                    }
                } else {
                    Log.d(TAG, "Not complete - non timed exercise");
                    // immediately stop the thread, and make something true/false
                    // timeThread.interrupt(); // pause thread

                    runOnUiThread(new Runnable() { // This section is looping way too fast - every iteration of the bottom code this happens twice
                        @Override
                        public void run() { // Updates the UI
                            topWorkoutTV.setText(myRoutine.getExerciseName(exerciseIndex));
                            try {
                                bottomWorkoutTV.setText(myRoutine.getExerciseDuration(exerciseIndex + 1) + " reps " + myRoutine.getExerciseName(exerciseIndex + 1));
                            } catch (Exception e) {
                                bottomWorkoutTV.setText("Finish!");
                            }
                            myTimer.setVisibility(View.INVISIBLE);
                        }
                    });
                    onRepExercise = true;
                    while (onRepExercise) {
                    }
                }
                exerciseIndex += 1;
                MediaPlayer ding = MediaPlayer.create(RoutineDisplay.this,R.raw.ding);
                // ding.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK); // might stop the mediaplayer from going to sleep? Not tested
                Log.d(TAG, "Play Ding Noise");
                ding.start();
                ding.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) { // method called at end of sound
                        mp.reset();
                        mp.release(); // seems to catch all media player errors/issues for sound clips
                    }
                });
            }
            routineOver();
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
    }
    
    public void routineOver() {
        Log.d(TAG, "routineOver()");
        MediaPlayer greatJob = MediaPlayer.create(RoutineDisplay.this,R.raw.briangreatjob);
        greatJob.start();
        finish();
    }

    public void pauseTimer() {
        if (isPaused) {
            myTimer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            myTimer.start();
            isPaused = false;
            pauseButton.setText("Pause");
            exitButton.setVisibility(View.INVISIBLE);
        } else {
            pauseOffset = SystemClock.elapsedRealtime() - myTimer.getBase();
            myTimer.stop();
            pauseButton.setText("Resume");
            exitButton.setVisibility(View.VISIBLE);
            isPaused = true;
            Log.d(TAG, "Pause");
        }
    }

    public void PauseButtonClick(View v) {
        pauseTimer();
    }

    public void ExitButtonClick(View v) {
        timeThread.interrupt();
        threadRunning = false;
        finish();
    }

    public void LayoutClick(View v) {
        Log.d(TAG, "LayoutClick invoked");
        if (onRepExercise && !isPaused) {
            onRepExercise = false;
            Log.d(TAG, "onRepExercise is False");
        }
    }

}
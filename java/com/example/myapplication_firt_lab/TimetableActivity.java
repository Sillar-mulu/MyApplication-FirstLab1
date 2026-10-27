
package com.example.myapplication_firt_lab;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class TimetableActivity extends AppCompatActivity {

    private static final String TAG = "CampusLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Log.d(TAG, "Timetable: onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Timetable: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Timetable: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Timetable: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Timetable: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Timetable: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Timetable: onDestroy");
    }
}

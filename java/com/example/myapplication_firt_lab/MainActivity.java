package com.example.myapplication_firt_lab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CampusLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvHomeEmpty = findViewById(R.id.tvHomeEmpty);
        TextView tvHomeError = findViewById(R.id.tvHomeError);
        // Temporary test conditions
        boolean hasUpcomingClasses = false;
        boolean loadingFailed = true;

// Hide both messages initially
        tvHomeEmpty.setVisibility(View.GONE);
        tvHomeError.setVisibility(View.GONE);

// Display the appropriate state
        if (loadingFailed) {
            tvHomeError.setVisibility(View.VISIBLE);
        } else if (!hasUpcomingClasses) {
            tvHomeEmpty.setVisibility(View.VISIBLE);
        }

        Log.d(TAG, "Home: onCreate");

        Button btnTimetable = findViewById(R.id.btnTimetable);
        Button btnNotices = findViewById(R.id.btnNotices);

        btnTimetable.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    TimetableActivity.class
            );
            startActivity(intent);
        });

        btnNotices.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    NoticesActivity.class
            );
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Home: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Home: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Home: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Home: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Home: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Home: onDestroy");
    }
}

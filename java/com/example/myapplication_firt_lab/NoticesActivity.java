
package com.example.myapplication_firt_lab;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class NoticesActivity extends AppCompatActivity {

    private static final String TAG = "CampusLifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);
        Log.d(TAG, "Notices: onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Notices: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Notices: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Notices: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Notices: onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Notices: onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Notices: onDestroy");
    }
}

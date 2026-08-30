package com.example.myapplication_lab1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimetableFragment extends Fragment {

    private LinearLayout timetableContainer;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_timetable,
                container,
                false
        );

        timetableContainer =
                view.findViewById(R.id.timetableContainer);

        showLoading();

        new Handler().postDelayed(() -> {

            boolean simulateError = false;

            if (simulateError) {
                showError();
            } else if (isOffline()) {
                showOffline();
            } else {
                showTimetable();
            }

        }, 1500);

        return view;
    }

    // ==============================
    // NORMAL TIMETABLE
    // ==============================

    private void showTimetable() {

        timetableContainer.removeAllViews();

        addHeading();

        addClass(
                "08:00 - 10:00",
                "Mobile Application Development",
                "Room 12"
        );

        addClass(
                "10:30 - 12:30",
                "Software Engineering",
                "Lab 2"
        );

        addClass(
                "14:00 - 16:00",
                "Database Systems",
                "Computer Lab"
        );
    }

    private void addHeading() {

        TextView title = new TextView(requireContext());

        title.setText("Timetable");
        title.setTextSize(28);
        title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        timetableContainer.addView(title);

        TextView subtitle = new TextView(requireContext());

        subtitle.setText("Today's Classes");
        subtitle.setTextSize(20);
        subtitle.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        subtitle.setPadding(
                0,
                20,
                0,
                20
        );

        timetableContainer.addView(subtitle);
    }

    private void addClass(
            String time,
            String subject,
            String location) {

        LinearLayout card =
                new LinearLayout(requireContext());

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                24,
                24,
                24,
                24
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                0,
                0,
                16
        );

        card.setLayoutParams(params);

        TextView timeText =
                new TextView(requireContext());

        timeText.setText(time);
        timeText.setTextSize(16);
        timeText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        TextView subjectText =
                new TextView(requireContext());

        subjectText.setText(subject);
        subjectText.setTextSize(20);
        subjectText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        subjectText.setPadding(
                0,
                8,
                0,
                8
        );

        TextView locationText =
                new TextView(requireContext());

        locationText.setText(location);
        locationText.setTextSize(16);

        card.addView(timeText);
        card.addView(subjectText);
        card.addView(locationText);

        timetableContainer.addView(card);
    }

    // ==============================
    // LOADING STATE
    // ==============================

    private void showLoading() {

        timetableContainer.removeAllViews();

        TextView loading =
                new TextView(requireContext());

        loading.setText(
                "Loading timetable...\n\nPlease wait."
        );

        loading.setTextSize(18);
        loading.setGravity(Gravity.CENTER);

        loading.setPadding(
                20,
                120,
                20,
                120
        );

        timetableContainer.addView(loading);
    }

    // ==============================
    // EMPTY STATE
    // ==============================

    private void showEmpty() {

        timetableContainer.removeAllViews();

        TextView empty =
                new TextView(requireContext());

        empty.setText(
                "No classes scheduled.\n\n" +
                        "You have no classes for today."
        );

        empty.setTextSize(18);
        empty.setGravity(Gravity.CENTER);

        empty.setPadding(
                20,
                120,
                20,
                120
        );

        timetableContainer.addView(empty);
    }

    // ==============================
    // OFFLINE STATE
    // ==============================

    private void showOffline() {

        timetableContainer.removeAllViews();

        TextView offline =
                new TextView(requireContext());

        offline.setText(
                "You are currently offline.\n\n" +
                        "Please check your internet connection."
        );

        offline.setTextSize(18);
        offline.setGravity(Gravity.CENTER);

        offline.setPadding(
                20,
                100,
                20,
                20
        );

        timetableContainer.addView(offline);

        Button retryButton =
                new Button(requireContext());

        retryButton.setText("Retry");

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        buttonParams.gravity = Gravity.CENTER;

        retryButton.setLayoutParams(buttonParams);

        retryButton.setOnClickListener(v -> {

            showLoading();

            new Handler().postDelayed(() -> {

                if (isOffline()) {
                    showOffline();
                } else {
                    showTimetable();
                }

            }, 1000);
        });

        timetableContainer.addView(retryButton);
    }

    // ==============================
    // ERROR STATE
    // ==============================

    private void showError() {

        timetableContainer.removeAllViews();

        TextView error =
                new TextView(requireContext());

        error.setText(
                "Something went wrong.\n\n" +
                        "We couldn't load your timetable."
        );

        error.setTextSize(18);
        error.setGravity(Gravity.CENTER);

        error.setPadding(
                20,
                100,
                20,
                20
        );

        timetableContainer.addView(error);

        Button retryButton =
                new Button(requireContext());

        retryButton.setText("Try Again");

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        buttonParams.gravity = Gravity.CENTER;

        retryButton.setLayoutParams(buttonParams);

        retryButton.setOnClickListener(v -> {

            showLoading();

            new Handler().postDelayed(() -> {

                if (isOffline()) {
                    showOffline();
                } else {
                    showTimetable();
                }

            }, 1000);
        });

        timetableContainer.addView(retryButton);
    }

    // ==============================
    // INTERNET CHECK
    // ==============================

    private boolean isOffline() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager)
                        requireContext().getSystemService(
                                Context.CONNECTIVITY_SERVICE
                        );

        if (connectivityManager == null) {
            return true;
        }

        NetworkInfo networkInfo =
                connectivityManager.getActiveNetworkInfo();

        return networkInfo == null
                || !networkInfo.isConnected();
    }
}
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

public class HomeFragment extends Fragment {

    private LinearLayout homeContainer;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_home,
                container,
                false
        );

        homeContainer = view.findViewById(R.id.homeContainer);

        showLoading();

        new Handler().postDelayed(() -> {

            boolean simulateError = false;
            boolean simulateEmpty = false;

            if (simulateError) {
                showError();

            } else if (simulateEmpty) {
                showEmpty();

            } else if (isOffline()) {
                showOffline();

            } else {
                showHome();
            }

        }, 1500);

        return view;
    }

    // ==============================
    // NORMAL HOME SCREEN
    // ==============================

    private void showHome() {

        homeContainer.removeAllViews();

        TextView title = new TextView(requireContext());
        title.setText("Campus Companion");
        title.setTextSize(28);
        title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        homeContainer.addView(title);

        TextView welcome = new TextView(requireContext());
        welcome.setText("Welcome");
        welcome.setTextSize(20);
        welcome.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );
        welcome.setPadding(0, 20, 0, 10);

        homeContainer.addView(welcome);

        TextView summary = new TextView(requireContext());
        summary.setText(
                "Here is your campus summary for today."
        );
        summary.setTextSize(16);
        summary.setPadding(0, 0, 0, 24);

        homeContainer.addView(summary);

        // Today's classes
        LinearLayout classCard =
                new LinearLayout(requireContext());

        classCard.setOrientation(
                LinearLayout.VERTICAL
        );

        classCard.setPadding(
                24,
                24,
                24,
                24
        );

        TextView classTitle =
                new TextView(requireContext());

        classTitle.setText("Today's Classes");
        classTitle.setTextSize(20);
        classTitle.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        TextView classOne =
                new TextView(requireContext());

        classOne.setText(
                "09:00 - Mobile Application Development"
        );

        classOne.setTextSize(16);
        classOne.setPadding(0, 12, 0, 8);

        TextView classTwo =
                new TextView(requireContext());

        classTwo.setText(
                "13:00 - Database Systems"
        );

        classTwo.setTextSize(16);

        classCard.addView(classTitle);
        classCard.addView(classOne);
        classCard.addView(classTwo);

        homeContainer.addView(classCard);

        // Important notice
        TextView noticeTitle =
                new TextView(requireContext());

        noticeTitle.setText(
                "IMPORTANT NOTICE"
        );

        noticeTitle.setTextSize(14);
        noticeTitle.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        noticeTitle.setPadding(
                0,
                30,
                0,
                8
        );

        homeContainer.addView(noticeTitle);

        TextView notice =
                new TextView(requireContext());

        notice.setText(
                "Registration Deadline\n" +
                        "Course registration closes on 5 September 2026."
        );

        notice.setTextSize(16);
        notice.setPadding(0, 0, 0, 24);

        homeContainer.addView(notice);

        Button timetableButton =
                new Button(requireContext());

        timetableButton.setText(
                "View Timetable"
        );
        timetableButton.setTextColor(
                android.graphics.Color.WHITE
        );

        timetableButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#1565C0")
                )
        );

        timetableButton.setMinHeight(48);

        timetableButton.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragment_container,
                            new TimetableFragment()
                    )
                    .commit();
        });

        homeContainer.addView(timetableButton);

        Button noticesButton =
                new Button(requireContext());

        noticesButton.setText(
                "View Notices"
        );
        noticesButton.setTextColor(
                android.graphics.Color.WHITE
        );

        noticesButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor("#1565C0")
                )
        );

        noticesButton.setMinHeight(48);

        noticesButton.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.fragment_container,
                            new NoticeFragment()
                    )
                    .commit();
        });

        homeContainer.addView(noticesButton);
    }

    // ==============================
    // LOADING STATE
    // ==============================

    private void showLoading() {

        homeContainer.removeAllViews();

        TextView loading =
                new TextView(requireContext());

        loading.setText(
                "Loading home screen...\n\nPlease wait."
        );

        loading.setTextSize(18);
        loading.setGravity(Gravity.CENTER);

        loading.setPadding(
                20,
                120,
                20,
                120
        );

        homeContainer.addView(loading);
    }

    // ==============================
    // EMPTY STATE
    // ==============================

    private void showEmpty() {

        homeContainer.removeAllViews();

        TextView empty =
                new TextView(requireContext());

        empty.setText(
                "Nothing scheduled today.\n\n" +
                        "You have no classes or important notices."
        );

        empty.setTextSize(18);
        empty.setGravity(Gravity.CENTER);

        empty.setPadding(
                20,
                120,
                20,
                120
        );

        homeContainer.addView(empty);
    }

    // ==============================
    // OFFLINE STATE
    // ==============================

    private void showOffline() {

        homeContainer.removeAllViews();

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

        homeContainer.addView(offline);

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
                    showHome();
                }

            }, 1000);
        });

        homeContainer.addView(retryButton);
    }

    // ==============================
    // ERROR STATE
    // ==============================

    private void showError() {

        homeContainer.removeAllViews();

        TextView error =
                new TextView(requireContext());

        error.setText(
                "Something went wrong.\n\n" +
                        "We couldn't load the home screen."
        );

        error.setTextSize(18);
        error.setGravity(Gravity.CENTER);

        error.setPadding(
                20,
                100,
                20,
                20
        );

        homeContainer.addView(error);

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
                    showHome();
                }

            }, 1000);
        });

        homeContainer.addView(retryButton);
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
package com.example.myapplication_lab1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoticeFragment extends Fragment {

    private LinearLayout noticesContainer;
    private EditText searchNotices;

    private Button btnAll;
    private Button btnImportant;
    private Button btnAcademic;

    private String currentFilter = "All";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_notices,
                container,
                false
        );

        // Connect Java to the XML components
        noticesContainer = view.findViewById(R.id.noticesContainer);
        searchNotices = view.findViewById(R.id.searchNotices);

        btnAll = view.findViewById(R.id.btnAll);
        btnImportant = view.findViewById(R.id.btnImportant);
        btnAcademic = view.findViewById(R.id.btnAcademic);

        // Set up search and filter buttons
        // Set up search and filter buttons
        setupSearch();
        setupFilters();

// First show loading state
        showLoading();

        new android.os.Handler().postDelayed(() -> {

            boolean simulateError = false;

            if (simulateError) {
                showError();
            } else if (isOffline()) {
                showOffline();
            } else {
                showNotices();
            }

        }, 1500);

        return view;
    }

    // ==============================
    // SEARCH
    // ==============================

    private void setupSearch() {

        searchNotices.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s,
                    int start,
                    int count,
                    int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s,
                    int start,
                    int before,
                    int count) {

                showNotices();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    // ==============================
    // FILTER BUTTONS
    // ==============================

    private void setupFilters() {

        btnAll.setOnClickListener(v -> {

            currentFilter = "All";
            showNotices();
        });

        btnImportant.setOnClickListener(v -> {

            currentFilter = "Important";
            showNotices();
        });

        btnAcademic.setOnClickListener(v -> {

            currentFilter = "Academic";
            showNotices();
        });
    }

    // ==============================
    // DISPLAY NOTICES
    // ==============================

    private void showNotices() {

        noticesContainer.removeAllViews();

        String searchText =
                searchNotices.getText().toString()
                        .toLowerCase()
                        .trim();

        int numberOfNotices = 0;

        // Notice 1
        if (matchesSearch(
                "IMPORTANT",
                "Registration Deadline",
                "Course registration closes on 5 September 2026.",
                searchText)
                && matchesFilter("Important")) {

            addNotice(
                    "IMPORTANT",
                    "Registration Deadline",
                    "Course registration closes on 5 September 2026.",
                    "30 Aug 2026"
            );

            numberOfNotices++;
        }

        // Notice 2
        if (matchesSearch(
                "ACADEMIC",
                "Lecture Schedule Updated",
                "The timetable has been updated. Please check your new lecture times.",
                searchText)
                && matchesFilter("Academic")) {

            addNotice(
                    "ACADEMIC",
                    "Lecture Schedule Updated",
                    "The timetable has been updated. Please check your new lecture times.",
                    "29 Aug 2026"
            );

            numberOfNotices++;
        }

        // Notice 3
        if (matchesSearch(
                "GENERAL",
                "Campus Clean-up",
                "Students are reminded about the campus clean-up exercise.",
                searchText)
                && matchesFilter("General")) {

            addNotice(
                    "GENERAL",
                    "Campus Clean-up",
                    "Students are reminded about the campus clean-up exercise.",
                    "28 Aug 2026"
            );

            numberOfNotices++;
        }

        // If no notices match
        if (numberOfNotices == 0) {
            showEmpty();
        }
    }

    // ==============================
    // FILTER CHECK
    // ==============================

    private boolean matchesFilter(String noticeType) {

        if (currentFilter.equals("All")) {
            return true;
        }

        return currentFilter.equalsIgnoreCase(noticeType);
    }

    // ==============================
    // SEARCH CHECK
    // ==============================

    private boolean matchesSearch(
            String priority,
            String title,
            String description,
            String search) {

        if (search.isEmpty()) {
            return true;
        }

        return priority.toLowerCase().contains(search)
                || title.toLowerCase().contains(search)
                || description.toLowerCase().contains(search);
    }

    // ==============================
    // CREATE NOTICE CARD
    // ==============================

    private void addNotice(
            String priority,
            String title,
            String description,
            String date) {

        LinearLayout card =
                new LinearLayout(requireContext());

        card.setOrientation(LinearLayout.VERTICAL);

        card.setPadding(
                32,
                24,
                32,
                24
        );

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        cardParams.setMargins(
                0,
                0,
                0,
                24
        );

        card.setLayoutParams(cardParams);

        // Priority
        TextView priorityText =
                new TextView(requireContext());

        priorityText.setText(priority);
        priorityText.setTextSize(14);
        priorityText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        // Title
        TextView titleText =
                new TextView(requireContext());

        titleText.setText(title);
        titleText.setTextSize(20);
        titleText.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        titleText.setPadding(0, 8, 0, 8);

        // Description
        TextView descriptionText =
                new TextView(requireContext());

        descriptionText.setText(description);
        descriptionText.setTextSize(16);

        // Date
        TextView dateText =
                new TextView(requireContext());

        dateText.setText("Date: " + date);
        dateText.setTextSize(14);

        dateText.setPadding(0, 12, 0, 0);

        // Add everything to card
        card.addView(priorityText);
        card.addView(titleText);
        card.addView(descriptionText);
        card.addView(dateText);

        // Add card to screen
        noticesContainer.addView(card);
    }

    // ==============================
    // EMPTY STATE
    // ==============================

    private void showEmpty() {

        noticesContainer.removeAllViews();

        TextView empty =
                new TextView(requireContext());

        empty.setText(
                "No notices found.\n\n" +
                        "Try another search or check again later."
        );

        empty.setTextSize(18);

        empty.setGravity(Gravity.CENTER);

        empty.setPadding(
                20,
                100,
                20,
                100
        );

        noticesContainer.addView(empty);
    }

    // ==============================
    // LOADING STATE
    // ==============================

    private void showLoading() {

        noticesContainer.removeAllViews();

        TextView loading =
                new TextView(requireContext());

        loading.setText(
                "Loading notices...\n\n" +
                        "Please wait."
        );

        loading.setTextSize(18);

        loading.setGravity(Gravity.CENTER);

        loading.setPadding(
                20,
                100,
                20,
                100
        );

        noticesContainer.addView(loading);
    }

    // ==============================
    // OFFLINE STATE
    // ==============================

    private void showOffline() {

        noticesContainer.removeAllViews();

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
                80,
                20,
                20
        );

        noticesContainer.addView(offline);

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

            new android.os.Handler().postDelayed(() -> {

                if (isOffline()) {
                    showOffline();
                } else {
                    showNotices();
                }

            }, 1000);
        });

        noticesContainer.addView(retryButton);
    }
    // ==============================
    // ERROR STATE
    // ==============================

    private void showError() {

        noticesContainer.removeAllViews();

        TextView error =
                new TextView(requireContext());

        error.setText(
                "Something went wrong.\n\n" +
                        "We couldn't load the notices."
        );

        error.setTextSize(18);

        error.setGravity(Gravity.CENTER);

        error.setPadding(
                20,
                80,
                20,
                20
        );

        noticesContainer.addView(error);

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

            new android.os.Handler().postDelayed(() -> {

                if (isOffline()) {
                    showOffline();
                } else {
                    showNotices();
                }

            }, 1000);
        });

        noticesContainer.addView(retryButton);
    }

    // ==============================
    // CHECK INTERNET CONNECTION
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
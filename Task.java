package com.example.todo_list;

import android.widget.TextView;

public class Task {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    private String title;
    private String dateAdded;
    private String dueDate;

    public TextView getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(TextView selectedDate) {
        this.selectedDate = selectedDate;
    }

    private TextView selectedDate;


    public Task(String title, String dateAdded, String dueDate) {
        this.title = title;
        this.dateAdded = dateAdded;
        this.dueDate = dueDate;
    }
    // Getters and setters
}

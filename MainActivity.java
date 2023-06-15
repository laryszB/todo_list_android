package com.example.todo_list;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private List<Task> tasks = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private EditText editTextTitle;
    private TextView selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // <-- setContentView powinno być wywołane najpierw

        recyclerView = findViewById(R.id.recyclerView);
        editTextTitle = findViewById(R.id.editTextTitle);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonPickDate = findViewById(R.id.buttonPickDate);
        selectedDate = findViewById(R.id.selectedDate); // <-- przenieś tę linię tutaj

        taskAdapter = new TaskAdapter(tasks, new TaskAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(int position) {
                editTask(position);
            }
        }, new TaskAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                deleteTask(position);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        buttonPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                                selectedDate.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        loadTasks();
    }

    private void addTask() {
        String title = editTextTitle.getText().toString().trim();
        String dueDate = selectedDate.getText().toString().trim();
        String dateAdded = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Task task = new Task(title, dateAdded, dueDate);
        tasks.add(task);

        taskAdapter.notifyDataSetChanged();

        editTextTitle.setText("");
        selectedDate.setText("No date selected");
    }

    private void editTask(int position) {
        // Place logic for editing the task
    }

    private void deleteTask(int position) {
        tasks.remove(position);
        taskAdapter.notifyItemRemoved(position);
    }

    private void loadTasks() {
        // Load tasks from storage (if needed)
    }
}

package com.example.todo_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final List<Task> tasks;
    private final OnEditClickListener onEditClickListener;
    private final OnDeleteClickListener onDeleteClickListener;

    public interface OnEditClickListener {
        void onEditClick(int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public TaskAdapter(List<Task> tasks, OnEditClickListener onEditClickListener, OnDeleteClickListener onDeleteClickListener) {
        this.tasks = tasks;
        this.onEditClickListener = onEditClickListener;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTitle.setText(task.getTitle());
        holder.textViewDateAdded.setText(task.getDateAdded());
        holder.textViewDueDate.setText(task.getDueDate());

        holder.buttonEdit.setOnClickListener(v -> onEditClickListener.onEditClick(position));
        holder.buttonDelete.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDateAdded, textViewDueDate;
        Button buttonEdit, buttonDelete;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDateAdded = itemView.findViewById(R.id.textViewDateAdded);
            textViewDueDate = itemView.findViewById(R.id.textViewDueDate);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}

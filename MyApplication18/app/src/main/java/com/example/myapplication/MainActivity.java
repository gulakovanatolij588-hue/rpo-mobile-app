package com.example.yourapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private List<String> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesList = new ArrayList<>();
        notesList.add("Заметка 1");
        notesList.add("Заметка 2");

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new NoteAdapter(notesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddNote).setOnClickListener(v -> showAddNoteDialog());
    }

    private void showAddNoteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Новая заметка");
        View view = getLayoutInflater().inflate(R.layout.dialog_add_note, null);
        EditText editText = view.findViewById(R.id.editNoteText);
        builder.setView(view);
        builder.setPositiveButton("Сохранить", (dialog, which) -> {
            String note = editText.getText().toString().trim();
            if (!note.isEmpty()) {
                notesList.add(note);
                adapter.notifyItemInserted(notesList.size() - 1);
                Toast.makeText(this, "Добавлено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Пустая заметка", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Отмена", null);
        builder.create().show();
    }
}
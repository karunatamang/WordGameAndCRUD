package com.example.wordgameandcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wordgameandcrud.adapter.StudentAdapter;
import com.example.wordgameandcrud.database.DbHelper;
import com.example.wordgameandcrud.model.Student;

import java.util.List;

public class ShowStudentActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);
        dbHelper = new DbHelper(this);
        recyclerView = findViewById(R.id.viewStudent);

        List<Student> students = dbHelper.getStudents();

        StudentAdapter studentAdapter = new StudentAdapter(this, students);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(studentAdapter);
    }
}


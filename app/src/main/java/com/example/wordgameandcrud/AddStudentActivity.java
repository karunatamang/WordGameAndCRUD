package com.example.wordgameandcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wordgameandcrud.database.DbHelper;
import com.example.wordgameandcrud.model.Student;

public class AddStudentActivity extends AppCompatActivity {
    EditText editTextN, editTextE, editTextP;
    Button buttonAdd, buttonShow;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        editTextN = findViewById(R.id.edName);
        editTextE = findViewById(R.id.edEmail);
        editTextP = findViewById(R.id.edPhone);
        buttonAdd = findViewById(R.id.btnAdd);
        buttonShow = findViewById(R.id.btnShow);

        dbHelper = new DbHelper(this);
        addStudent();
        showList();
    }

    public void addStudent() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editTextN.getText().toString();
                String e = editTextE.getText().toString();
                String p = editTextP.getText().toString();

                Student student = new Student(0, n, e, p);

                if (dbHelper.addStudent(student)) {
                    Toast.makeText(AddStudentActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    editTextN.setText("");
                    editTextE.setText("");
                    editTextP.setText("");
                }
            }
        });
    }

    private void showList() {
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddStudentActivity.this, ShowStudentActivity.class);
                startActivity(intent);
            }
        });

    }
}


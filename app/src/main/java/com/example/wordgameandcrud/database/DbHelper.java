package com.example.wordgameandcrud.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.wordgameandcrud.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_student";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addStudent(Student student) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO students (name, email, phone) VALUES ('" + student.getName() + "','" + student.getEmail() + "','" + student.getPhone() + "')");
            return true;
        } catch (Exception e) {
            Log.d("DBEx", e.toString());
            return false;
        }
    }
    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM students", null);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    studentList.add(new Student(cursor.getInt(0), cursor.getString(1),
                            cursor.getString(2), cursor.getString(3)));
                }
            }
            return studentList;

        } catch (Exception e) {
            Log.d("DBEx", e.toString());
        }
        return studentList;
    }

    public boolean deleteData(Student student) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM students WHERE id='" + student.getId() + "' ");
            return true;
        } catch (Exception e) {
            Log.d("DBDeleteEx", e.toString());
            return false;
        }

    }

    public boolean updateStudent(Student student) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE students SET name = '" + student.getName() + "', email = '" + student.getEmail() + "', phone = '" + student.getPhone() + "' WHERE id='" + student.getId() + "' ");

            return true;
        } catch (Exception e) {
            Log.d("DBUpdateEx", e.toString());
            return false;
        }
    }



}

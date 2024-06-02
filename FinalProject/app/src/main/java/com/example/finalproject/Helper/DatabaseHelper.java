package com.example.finalproject.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalproject.Model.Student;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "favorites";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_GPA = "gpa";
    private static final String COLUMN_COURSE = "course";
    private static final String COLUMN_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_COURSE + " TEXT, " +
                COLUMN_GPA + " REAL, " +
                COLUMN_IMAGE + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addFavorite(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, student.getId());
        contentValues.put(COLUMN_NAME, student.getName());
        contentValues.put(COLUMN_EMAIL, student.getEmail());
        contentValues.put(COLUMN_COURSE, String.valueOf(student.getCourses()));
        contentValues.put(COLUMN_GPA, student.getGpa());
        contentValues.put(COLUMN_IMAGE, student.getImage());
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean removeFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_ID + "=?";
        String[] selectionargs = new String[]{String.valueOf(id)};
        boolean student = db.delete("favorites", selection, selectionargs) > 0;
        db.close();
        return student;
    }

    public Cursor getAllFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean isFavorite(int studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(studentId)};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }

}

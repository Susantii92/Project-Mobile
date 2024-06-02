package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.FastAPI.RetrofitClient;
import com.example.finalproject.FastAPI.StudentApi;
import com.example.finalproject.FavoriteAdapter;
import com.example.finalproject.Helper.DatabaseHelper;
import com.example.finalproject.R;
import com.example.finalproject.Model.Student;
import com.example.finalproject.StudentAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private ArrayList<Student> favoriteStudents = new ArrayList<>();
    private DatabaseHelper dbHelper;
    private ProgressBar favoriteProgressBar;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        favoriteProgressBar = root.findViewById(R.id.favoriteProgressBar);
        dbHelper = new DatabaseHelper(getContext());
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FavoriteAdapter(getContext(), favoriteStudents);
        recyclerView.setAdapter(adapter);
        loadFavorites();


        return root;
    }

    private void loadFavorites() {

        recyclerView.setVisibility(View.GONE);
        favoriteProgressBar.setVisibility(View.VISIBLE);

        new Thread(() -> {
            Cursor cursor = dbHelper.getAllFavorites();
            favoriteStudents.clear();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                double gpa = cursor.getDouble(cursor.getColumnIndexOrThrow("gpa"));
                String courseString = cursor.getString(cursor.getColumnIndexOrThrow("course"));
                String[] courses = courseString.split(",");
                ArrayList<String> coursesList = new ArrayList<>(Arrays.asList(courses));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));

                // Buat objek Student
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setEmail(email);
                student.setGpa(gpa);
                student.setImage(image);
                student.setCourses(coursesList);

                favoriteStudents.add(student);
            }
            cursor.close();

            handler.postDelayed(() -> {
                favoriteProgressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }, 1000);
        }).start();
    }
}
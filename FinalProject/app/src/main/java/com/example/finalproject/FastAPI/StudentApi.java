package com.example.finalproject.FastAPI;

import com.example.finalproject.Model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StudentApi {
    @GET("/api/v1/students")
    Call<List<Student>> getStudents();
    @GET("/api/v1/students/{id}")
    Call<Student> getStudent(@Path("id") int id);
}

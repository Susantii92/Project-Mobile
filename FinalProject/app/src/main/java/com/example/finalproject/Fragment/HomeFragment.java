package com.example.finalproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.ProfileActivity;
import com.example.finalproject.R;
import com.example.finalproject.FastAPI.RetrofitClient;
import com.example.finalproject.FastAPI.StudentApi;
import com.example.finalproject.Model.Student;
import com.example.finalproject.StudentAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private SearchView searchView;
    private ProgressBar progressBar, searchProgressBar;
    private TextView noResultsTextView;
    private Handler handler = new Handler();
    private static final int DELAY_TIME = 1000;
    private StudentApi studentApi;
    private Runnable searchRunnable;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        searchView = root.findViewById(R.id.searchView);
        progressBar = root.findViewById(R.id.pb);
        searchProgressBar = root.findViewById(R.id.search);
        noResultsTextView = root.findViewById(R.id.noResultsTextView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        studentApi = RetrofitClient.getInstance().getStudentApi();

        loadStudents("");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                executeSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchRunnable != null) {
                    handler.removeCallbacks(searchRunnable);
                }
                searchRunnable = () -> executeSearch(newText);
                handler.postDelayed(searchRunnable, DELAY_TIME);
                return false;
            }
        });

        return root;
    }

    private void executeSearch(String query) {
        searchProgressBar.setVisibility(View.VISIBLE);
        handler.postDelayed(() -> {
            loadStudents(query);
            searchProgressBar.setVisibility(View.GONE);
        }, DELAY_TIME);
    }

    //Retrofit
    private void loadStudents(String query) {
        progressBar.setVisibility(View.VISIBLE);
        Call<List<Student>> call = studentApi.getStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Student> studentList = response.body();
                    if (!query.isEmpty()) {
                        studentList = filterStudents(studentList, query);
                    }
                    if (studentList.isEmpty()) {
                        noResultsTextView.setVisibility(View.VISIBLE);
                    } else {
                        noResultsTextView.setVisibility(View.GONE);
                    }
                    studentAdapter = new StudentAdapter(studentList, HomeFragment.this::onStudentClick);
                    recyclerView.setAdapter(studentAdapter);
                    studentAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to load students", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Student> filterStudents(List<Student> studentList, String query) {
        List<Student> filteredList = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(student);
            }
        }
        return filteredList;
    }

    private void onStudentClick(Student student) {
        Intent intent = new Intent(requireContext(), ProfileActivity.class);
        intent.putExtra("studentId", student.getId());
        intent.putExtra("studentName", student.getName());
        intent.putExtra("studentAge", student.getAge());
        intent.putExtra("studentGender", student.getGender());
        intent.putExtra("studentAddressStreet", student.getAddress().getStreet());
        intent.putExtra("studentAddressCity", student.getAddress().getCity());
        intent.putExtra("studentAddressZip", student.getAddress().getZip());
        intent.putExtra("studentAddressCountry", student.getAddress().getCountry());
        intent.putExtra("studentEmail", student.getEmail());
        intent.putExtra("studentPhone", student.getPhone());
        intent.putStringArrayListExtra("studentCourses", new ArrayList<>(student.getCourses()));
        intent.putExtra("studentGpa", student.getGpa());
        intent.putExtra("studentImage", student.getImage());
        startActivity(intent);
    }
}

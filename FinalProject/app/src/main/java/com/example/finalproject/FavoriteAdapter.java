package com.example.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Helper.DatabaseHelper;
import com.example.finalproject.Model.Student;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private ArrayList<Student> favoriteStudents;
    private DatabaseHelper dbHelper;
    private Context context;

    public FavoriteAdapter(Context context, ArrayList<Student> favoriteStudents) {
        this.favoriteStudents = favoriteStudents;
        this.dbHelper = new DatabaseHelper(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = favoriteStudents.get(position);

        holder.nameTextView.setText(student.getName());
        holder.emailTextView.setText(student.getEmail());
        holder.gpaTextView.setText(String.valueOf(student.getGpa()));
        holder.photoImageView.setImageResource(R.drawable.harvard);

        //data mahasiswa
        StringBuilder coursesBuilder = new StringBuilder();
        for (String course : student.getCourses()) {
            coursesBuilder.append(course).append(", ");
        }
        String allCourses = coursesBuilder.toString();

        if (!allCourses.isEmpty()) {
            allCourses = allCourses.substring(0, allCourses.length() - 2);
        }
        holder.courseTextView.setText(allCourses);

        holder.loveButton.setOnClickListener(view -> {
            int studentid = student.getId();
            dbHelper.removeFavorite(studentid);
            favoriteStudents.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(view.getContext(), "Profil dihapus dari Favorit", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return favoriteStudents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView, gpaTextView, courseTextView ;
        ImageView photoImageView;
        ImageButton loveButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            gpaTextView = itemView.findViewById(R.id.gpaTextView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            loveButton = itemView.findViewById(R.id.loveButton);
        }
    }
}

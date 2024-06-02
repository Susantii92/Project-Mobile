package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.Model.Student;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> students;
    private OnStudentClickListener onStudentClickListener;

    public StudentAdapter(List<Student> students, OnStudentClickListener onStudentClickListener) {
        this.students = students;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.bind(student, onStudentClickListener);

        holder.photoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStudentClickListener.onStudentClick(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public interface OnStudentClickListener {
        void onStudentClick(Student student);
    }

    //sub kelas
    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView gpaTextView;
        TextView emailTextView;
        TextView courseTextView;
        ImageView photoImageView;
        ImageButton loveButton;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            gpaTextView = itemView.findViewById(R.id.gpaTextView);
            courseTextView = itemView.findViewById(R.id.courseTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
            loveButton = itemView.findViewById(R.id.loveButton);
        }

        public void bind(final Student student, final OnStudentClickListener onStudentClickListener) {
            nameTextView.setText(student.getName());
            emailTextView.setText(student.getEmail());
            gpaTextView.setText(String.valueOf(student.getGpa()));

            String coursesString = String.join(", ", student.getCourses());
            courseTextView.setText(coursesString);
            photoImageView.setImageResource(R.drawable.harvard);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onStudentClickListener != null) {
                        onStudentClickListener.onStudentClick(student);
                    }
                }
            });
        }
    }
}
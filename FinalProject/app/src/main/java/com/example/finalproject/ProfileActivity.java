package com.example.finalproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.Helper.DatabaseHelper;
import com.example.finalproject.Model.Student;
import java.util.ArrayList;
import java.util.Random;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton loveButton;
    private boolean isLoved = false;
    private DatabaseHelper dbHelper;
    private int studentId;
    private ProgressBar profileProgressBar;
    private Handler handler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileProgressBar = findViewById(R.id.profileProgressBar);
        profileProgressBar.setVisibility(View.VISIBLE);
        handler = new Handler(Looper.getMainLooper());

        dbHelper = new DatabaseHelper(this);
        loveButton = findViewById(R.id.loveButton);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            studentId = extras.getInt("studentId");
            String studentName = extras.getString("studentName");
            int studentAge = extras.getInt("studentAge");
            String studentGender = extras.getString("studentGender");
            String studentAddressStreet = extras.getString("studentAddressStreet");
            String studentAddressCity = extras.getString("studentAddressCity");
            String studentAddressZip = extras.getString("studentAddressZip");
            String studentAddressCountry = extras.getString("studentAddressCountry");
            String studentEmail = extras.getString("studentEmail");
            String studentPhone = extras.getString("studentPhone");
            ArrayList<String> studentCourses = extras.getStringArrayList("studentCourses");
            double studentGpa = extras.getDouble("studentGpa");
            String studentImage = extras.getString("studentImage");


            handler.postDelayed(() -> {
            TextView nameTextView = findViewById(R.id.nameTextView);
            TextView ageTextView = findViewById(R.id.ageTextView);
            TextView genderTextView = findViewById(R.id.genderTextView);
            TextView streetTextView = findViewById(R.id.streetTextView);
            TextView cityTextView = findViewById(R.id.cityTextView);
            TextView zipTextView = findViewById(R.id.zipTextView);
            TextView countryTextView = findViewById(R.id.countryTextView);
            TextView emailTextView = findViewById(R.id.emailTextView);
            TextView phoneTextView = findViewById(R.id.phoneTextView);
            TextView coursesTextView = findViewById(R.id.courseTextView);
            TextView gpaTextView = findViewById(R.id.gpaTextView);
            ImageView photoImageView = findViewById(R.id.photoImageView);
            ImageView sampulImageView = findViewById(R.id.sampulImageView);

            nameTextView.setText(studentName);
            ageTextView.setText(String.valueOf(studentAge));
            genderTextView.setText(studentGender);
            streetTextView.setText(studentAddressStreet);
            cityTextView.setText(studentAddressCity);
            zipTextView.setText(studentAddressZip);
            countryTextView.setText(studentAddressCountry);
            emailTextView.setText(studentEmail);
            phoneTextView.setText(studentPhone);


            String coursesString = "";
            if (studentCourses != null && !studentCourses.isEmpty()) {
                coursesString = TextUtils.join(", ", studentCourses);
            }

            coursesTextView.setText(coursesString);
            gpaTextView.setText(String.valueOf(studentGpa));
            photoImageView.setImageResource(R.drawable.harvard);


            int sampulImageResId = getSampulImageResId(studentGender);
            sampulImageView.setImageResource(sampulImageResId);

            ImageButton backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(v -> finish());

            // Periksa status favorit dari database
            isLoved = dbHelper.isFavorite(studentId);
            updateLoveButton();

            loveButton.setOnClickListener(view -> {
                isLoved = !isLoved;
                if (isLoved) {
                    addFavoriteToDatabase();
                } else {
                    removeFavoriteFromDatabase();
                }
                updateLoveButton();
            });
            profileProgressBar.setVisibility(View.GONE);
            }, 1000);
        } else {
            profileProgressBar.setVisibility(View.GONE);
        }
    }

    private int getSampulImageResId(String gender) {
        Random random = new Random();
        int randomIndex;

        if (gender.equalsIgnoreCase("female")) {
            randomIndex = random.nextInt(5) + 1;
        } else {
            randomIndex = random.nextInt(5) + 6;
        }

        String resourceName = "sampul_" + randomIndex;
        return getResources().getIdentifier(resourceName, "drawable", getPackageName());
    }


    private void addFavoriteToDatabase() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int studentId = extras.getInt("studentId");
            String studentName = extras.getString("studentName");
            String studentEmail = extras.getString("studentEmail");
            ArrayList<String> studentCourses = extras.getStringArrayList("studentCourses");
            double studentGpa = extras.getDouble("studentGpa");
            String studentImage = extras.getString("studentImage");

            Student student = new Student();
            student.setId(studentId);
            student.setName(studentName);
            student.setEmail(studentEmail);
            student.setCourses(studentCourses != null ? studentCourses : new ArrayList<>());
            student.setGpa(studentGpa);
            student.setImage(studentImage);


            boolean success = dbHelper.addFavorite(student);
            if (success) {
                isLoved = true;
                loveButton.setImageResource(R.drawable.love_klik);
                Toast.makeText(this, "Profil ditambahkan ke Favorit", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal menambahkan profil ke Favorit", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void removeFavoriteFromDatabase() {
        boolean success = dbHelper.removeFavorite(studentId);
        if (success) {
            isLoved = false;
            Toast.makeText(this, "Profil dihapus dari Favorit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal menghapus profil dari Favorit", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLoveButton() {
        if (isLoved) {
            loveButton.setImageResource(R.drawable.love_klik);
        } else {
            loveButton.setImageResource(R.drawable.love);
        }
    }
}
//package com.example.finalproject;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class RegisterActivity extends AppCompatActivity {
//
//    EditText editTextUsername, editTextEmail, editTextPassword;
//    SharedPreferences sharedPreferences;
//    Button buttonRegister;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        // Temukan tombol panah kembali
//        ImageButton backButton = findViewById(R.id.backButton);
//
//        // Tambahkan fungsi klik untuk tombol panah kembali
//        backButton.setOnClickListener(v -> {
//            // Kembali ke LoginActivity
//            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            finish();
//        });
//
//
//        // Inisialisasi SharedPreferences dengan nama MyPrefs
//        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        setContentView(R.layout.activity_register);
//
//        editTextUsername = findViewById(R.id.et_username);
//        editTextEmail = findViewById(R.id.et_email);
//        editTextPassword = findViewById(R.id.et_password);
//        buttonRegister = findViewById(R.id.btn_register);
//
//        buttonRegister.setOnClickListener(v -> {
//            String username = editTextUsername.getText().toString();
//            String email = editTextEmail.getText().toString();
//            String password = editTextPassword.getText().toString();
//
//            if (username.isEmpty()) {
//                editTextUsername.setError("Username cannot be empty");
//                return;
//            }
//
//            if (email.isEmpty()) {
//                editTextEmail.setError("Email cannot be empty");
//                return;
//            }
//
//            if (password.isEmpty()) {
//                editTextPassword.setError("Password cannot be empty");
//                return;
//            }
//
//            // Menyimpan data username, email, dan password di SharedPreferences
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("Username", username);
//            editor.putString("Email", email);
//            editor.putString("Password", password);
//            editor.apply();
//
//            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
//
//            // Kembali ke LoginActivity
//            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//            finish();
//        });
//    }
//}

package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    SharedPreferences sharedPreferences;
    Button buttonRegister;
    TextView logindisini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        editTextUsername = findViewById(R.id.et_username);
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonRegister = findViewById(R.id.btn_register);
        logindisini = findViewById(R.id.logindisini);

        logindisini.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });

        buttonRegister.setOnClickListener(v -> {
            String username = editTextUsername.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            if (username.isEmpty()) {
                editTextUsername.setError("Username cannot be empty");
                return;
            }

            if (email.isEmpty()) {
                editTextEmail.setError("Email cannot be empty");
                return;
            }

            if (password.isEmpty()) {
                editTextPassword.setError("Password cannot be empty");
                return;
            }

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Username", username);
            editor.putString("Email", email);
            editor.putString("Password", password);
            editor.apply();

            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}

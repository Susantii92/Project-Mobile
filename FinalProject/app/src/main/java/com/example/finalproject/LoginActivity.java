package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    SharedPreferences sharedPreferences;
    Button buttonLogin, buttonRegister;
    TextView daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        setContentView(R.layout.activity_login);

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        editTextUsername = findViewById(R.id.et_username);
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        buttonLogin = findViewById(R.id.btn_login);
        buttonRegister = findViewById(R.id.btn_register);
        daftar = findViewById(R.id.daftar);

        buttonLogin.setOnClickListener(v -> {
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

            String savedUsername = sharedPreferences.getString("Username", "");
            String savedEmail = sharedPreferences.getString("Email", "");
            String savedPassword = sharedPreferences.getString("Password", "");

            // Membandingkan
            if (username.equals(savedUsername) && email.equals(savedEmail) && password.equals(savedPassword)) {
                Toast.makeText(LoginActivity.this, "Selamat datang " + username, Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Username", username);
                editor.putString("Email", email);
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "The username, email, or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        buttonRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        daftar.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            finish();
        });

        int theme = sharedPreferences.getInt("theme", 0);
        if (theme == 1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}

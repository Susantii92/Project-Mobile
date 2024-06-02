package com.example.finalproject.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.finalproject.LoginActivity;
import com.example.finalproject.R;

public class UserFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private TextView tvUsername, tvEmail;
    private Button buttonLogout;
    private ProgressBar progressBar;
    private View contentLayout;

    public UserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", requireActivity().MODE_PRIVATE);

        tvUsername = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_number);
        buttonLogout = view.findViewById(R.id.button_logout);
        progressBar = view.findViewById(R.id.progressBar);
        contentLayout = view.findViewById(R.id.contentLayout);

        progressBar.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username = sharedPreferences.getString("Username", "");
                String email = sharedPreferences.getString("Email", "");

                tvUsername.setText(username);
                tvEmail.setText(email);

                progressBar.setVisibility(View.GONE);
                contentLayout.setVisibility(View.VISIBLE);
            }
        }, 1000);


        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hapus status login
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("isLoggedIn");
                editor.apply();

                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        return view;
    }
}

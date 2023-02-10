package com.example.uitopenremote_group11;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.uitopenremote_group11.Helper.LoginHelper;

public class SignUpActivity extends AppCompatActivity {
    EditText username_ET, password_ET, confirmPassword_ET;
    LoginHelper signup_db;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
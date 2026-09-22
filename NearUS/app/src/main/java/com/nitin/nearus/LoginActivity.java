package com.nitin.nearus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEt, passwordEt;

    Button loginBtn;

    TextView registerText;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);

        loginBtn = findViewById(R.id.loginBtn);

        registerText = findViewById(R.id.registerText);

        auth = FirebaseAuth.getInstance();

        // LOGIN

        loginBtn.setOnClickListener(v -> {

            String email = emailEt.getText().toString().trim();

            String password = passwordEt.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){

                Toast.makeText(this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT).show();

            } else {

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {

                            if(task.isSuccessful()){

                                Toast.makeText(this,
                                        "Login Successful",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(
                                        LoginActivity.this,
                                        HomeActivity.class);

                                startActivity(intent);

                                finish();

                            } else {

                                Toast.makeText(this,
                                        "Invalid Email or Password",
                                        Toast.LENGTH_SHORT).show();

                            }

                        });

            }

        });

        // REGISTER TEXT

        registerText.setOnClickListener(v -> {

            Intent intent = new Intent(
                    LoginActivity.this,
                    RegisterActivity.class);

            startActivity(intent);

            finish();

        });

    }
}
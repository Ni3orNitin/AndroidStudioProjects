package com.nitin.nearus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText nameEt, emailEt, passwordEt;

    Button createBtn;

    TextView loginText;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEt = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);
        passwordEt = findViewById(R.id.passwordEt);

        createBtn = findViewById(R.id.createBtn);

        loginText = findViewById(R.id.loginText);

        auth = FirebaseAuth.getInstance();

        // CREATE ACCOUNT

        createBtn.setOnClickListener(v -> {

            String email = emailEt.getText().toString().trim();

            String password = passwordEt.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){

                Toast.makeText(this,
                        "Fill all fields",
                        Toast.LENGTH_SHORT).show();

            } else {

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {

                            if(task.isSuccessful()){

                                Toast.makeText(this,
                                        "Account Created",
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(
                                        RegisterActivity.this,
                                        HomeActivity.class);

                                startActivity(intent);

                                finish();

                            } else {

                                Toast.makeText(this,
                                        task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }

                        });

            }

        });

        // LOGIN TEXT

        loginText.setOnClickListener(v -> {

            Intent intent = new Intent(
                    RegisterActivity.this,
                    LoginActivity.class);

            startActivity(intent);

            finish();

        });

    }
}
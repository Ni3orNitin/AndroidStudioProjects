package com.nitin.registerandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    EditText lname, lpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        lname = findViewById(R.id.lname);
        lpass = findViewById(R.id.lpass);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View view) {

        String loginName = lname.getText().toString();
        String loginPass = lpass.getText().toString();

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String pass = intent.getStringExtra("pass");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");

        if (name.equals(loginName) && pass.equals(loginPass)){
            Intent intent1 = new Intent(Login.this,Home.class);
            intent1.putExtra("name",name);
            intent1.putExtra("pass",pass);
            intent1.putExtra("email",email);
            intent1.putExtra("number",number);
            startActivity(intent1);
        }
        else {
            lname.setError("Invalid Username");
            lpass.setError("Invalid Password");
        }



    }
}
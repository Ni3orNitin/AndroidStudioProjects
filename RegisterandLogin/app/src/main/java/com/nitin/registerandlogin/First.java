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

public class First extends AppCompatActivity {

    EditText rname, rpass, remail, rnumber;

    DBhelper db1 = new DBhelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        rname = findViewById(R.id.rname);
        rpass = findViewById(R.id.rpass);
        remail = findViewById(R.id.remail);
        rnumber = findViewById(R.id.rnumber);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void register(View view) {

        String name = rname.getText().toString();
        String pass = rpass.getText().toString();
        String email = remail.getText().toString();
        String number = rnumber.getText().toString();

        Intent intent = new Intent(First.this, Login.class);
        intent.putExtra("name", name);
        intent.putExtra("pass", pass);
        intent.putExtra("email", email);
        intent.putExtra("number", number);
        startActivity(intent);


        db1.registerdb1(name,pass,email,number);




    }
}
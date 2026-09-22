package com.nitin.registerandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String pass = intent.getStringExtra("pass");
        String email = intent.getStringExtra("email");
        String number = intent.getStringExtra("number");

        TextView textView11 = findViewById(R.id.hname);
        textView11.setText(name);

        TextView textView9 = findViewById(R.id.hemail);
        textView9.setText(email);

        TextView textView12 = findViewById(R.id.hpass);
        textView12.setText(pass);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }


    public void showmsg (View view) {
        TextView hname = findViewById(R.id.hname);
        TextView hemail = findViewById(R.id.hemail);
        TextView hpass = findViewById(R.id.hpass);

        String fname = hname.getText().toString();
        String femail = hemail.getText().toString();
        String fpass = hpass.getText().toString();

        Intent intent3 = new Intent(Home.this, Showmsg.class);
        intent3.putExtra("name",fname);
        intent3.putExtra("email",femail);
        intent3.putExtra("pass",fpass);
        startActivity(intent3);
    }
}
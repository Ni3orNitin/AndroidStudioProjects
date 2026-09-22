package com.nitin.loginandshowdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View view){
        String uname= username.getText().toString();
        String upass= password.getText().toString();

        Bundle bundle = new Bundle();

        bundle.putString("uname", uname);
        bundle.putString("upass", upass);

        Intent intent = new Intent(MainActivity.this,showUserDAta.class);

        intent.putExtras(bundle);
        startActivities(intent);

    }

}
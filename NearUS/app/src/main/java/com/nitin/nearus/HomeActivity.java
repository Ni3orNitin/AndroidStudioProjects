package com.nitin.nearus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Map dashboard clicks directly to your aesthetic creation layouts
        View startBtn = findViewById(R.id.startRoomQuickBtn);
        View joinBtn = findViewById(R.id.joinRoomQuickBtn);

        View.OnClickListener routeToRooms = v -> {
            Intent intent = new Intent(HomeActivity.this, RoomsActivity.class);
            startActivity(intent);
        };

        if (startBtn != null) startBtn.setOnClickListener(routeToRooms);
        if (joinBtn != null) joinBtn.setOnClickListener(routeToRooms);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_home);
            bottomNav.setOnItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_rooms) {
                    startActivity(new Intent(HomeActivity.this, RoomsActivity.class));
                    return true;
                }
                return id == R.id.nav_home;
            });
        }
    }
}
package com.nitin.nearus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StartHangoutActivity extends AppCompatActivity {

    EditText roomCodeEt;

    Button createRoomBtn, joinRoomBtn;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_hangout);

        roomCodeEt = findViewById(R.id.roomCodeEt);

        createRoomBtn = findViewById(R.id.createRoomBtn);

        joinRoomBtn = findViewById(R.id.joinRoomBtn);

        databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("rooms");

        // CREATE ROOM

        createRoomBtn.setOnClickListener(v -> {

            String chars =
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

            StringBuilder room =
                    new StringBuilder();

            for (int i = 0; i < 5; i++) {

                int index =
                        (int)(Math.random()
                                * chars.length());

                room.append(
                        chars.charAt(index)
                );

            }

            String roomId =
                    room.toString();

            databaseReference
                    .child(roomId)
                    .setValue("created")

                    .addOnSuccessListener(unused -> {

                        Toast.makeText(
                                this,
                                "Room Created",
                                Toast.LENGTH_SHORT
                        ).show();

                        Intent intent =
                                new Intent(
                                        StartHangoutActivity.this,
                                        RoomActivity.class
                                );

                        intent.putExtra(
                                "ROOM_ID",
                                roomId
                        );

                        startActivity(intent);

                    })

                    .addOnFailureListener(e -> {

                        Toast.makeText(
                                this,
                                e.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();

                    });

        });

        // JOIN ROOM

        joinRoomBtn.setOnClickListener(v -> {

            String roomId =
                    roomCodeEt.getText()
                            .toString()
                            .trim();

            if(roomId.isEmpty()){

                Toast.makeText(
                        this,
                        "Enter Room Code",
                        Toast.LENGTH_SHORT
                ).show();

                return;

            }

            Intent intent =
                    new Intent(
                            StartHangoutActivity.this,
                            RoomActivity.class
                    );

            intent.putExtra(
                    "ROOM_ID",
                    roomId
            );

            startActivity(intent);

        });

    }
}
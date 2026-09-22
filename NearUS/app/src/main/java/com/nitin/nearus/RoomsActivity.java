package com.nitin.nearus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RoomsActivity extends AppCompatActivity {

    private EditText roomCodeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        roomCodeEt = findViewById(R.id.roomCodeEt);

        Button createBtn =
                findViewById(R.id.createRoomBtn);

        Button joinBtn =
                findViewById(R.id.joinRoomBtn);



        // CREATE ROOM

        createBtn.setOnClickListener(v -> {

            String roomCode =
                    "NUS" +
                            (1000 + (int)(Math.random() * 9000));

            DatabaseReference roomRef =
                    FirebaseDatabase
                            .getInstance()
                            .getReference("rooms")
                            .child(roomCode);

            HashMap<String,Object> roomData =
                    new HashMap<>();

            roomData.put(
                    "roomId",
                    roomCode
            );

            roomData.put(
                    "createdAt",
                    System.currentTimeMillis()
            );

            roomRef
                    .setValue(roomData)
                    .addOnSuccessListener(unused -> {

                        Toast.makeText(
                                this,
                                "Room Created : " + roomCode,
                                Toast.LENGTH_SHORT
                        ).show();

                        launchActiveRoomSession(
                                roomCode
                        );

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

        joinBtn.setOnClickListener(v -> {

            String enteredCode =
                    roomCodeEt
                            .getText()
                            .toString()
                            .trim()
                            .toUpperCase();

            if(enteredCode.isEmpty()){

                Toast.makeText(
                        this,
                        "Enter Room ID",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            FirebaseDatabase
                    .getInstance()
                    .getReference("rooms")
                    .child(enteredCode)
                    .get()
                    .addOnSuccessListener(snapshot -> {

                        if(snapshot.exists()){

                            launchActiveRoomSession(
                                    enteredCode
                            );

                        }
                        else{

                            Toast.makeText(
                                    this,
                                    "Room Not Found",
                                    Toast.LENGTH_SHORT
                            ).show();

                        }

                    });

        });

    }



    private void launchActiveRoomSession(
            String roomId
    ){

        Intent intent =
                new Intent(
                        RoomsActivity.this,
                        RoomActivity.class
                );

        intent.putExtra(
                "ROOM_ID",
                roomId
        );

        startActivity(intent);

    }

}
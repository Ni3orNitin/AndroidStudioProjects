package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.helloText)
        val button = findViewById<Button>(R.id.clickBtn)

        button.setOnClickListener {
            text.text = "Welcome to Android 🚀"
        }
    }
}

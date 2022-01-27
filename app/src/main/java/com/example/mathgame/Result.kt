package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val result = findViewById<TextView>(R.id.textViewResult)
        val playAgain = findViewById<Button>(R.id.buttonAgain)
        val exit = findViewById<Button>(R.id.buttonExit)
        val score = intent.getIntExtra("score", 0)
        val userScore: String = String.format("%d", score)
        result.setText("Tvé konečné skóre : $userScore")

        playAgain.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        exit.setOnClickListener {
            finish()
        }
    }
}
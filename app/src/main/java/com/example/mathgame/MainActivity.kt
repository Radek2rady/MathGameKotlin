package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addition = findViewById<Button>(R.id.buttonAdd)
        val subtraction = findViewById<Button>(R.id.buttonSub)
        val multi = findViewById<Button>(R.id.buttonMulti)

        addition.setOnClickListener {
            val intent = Intent(this, GameAddition::class.java)
            startActivity(intent)
            finish()
        }

        subtraction.setOnClickListener {
            val intent = Intent(this, GameSubtraction::class.java)
            startActivity(intent)
            finish()
        }

        multi.setOnClickListener {
            val intent = Intent(this, GameMulti::class.java)
            startActivity(intent)
            finish()
        }



    }
}
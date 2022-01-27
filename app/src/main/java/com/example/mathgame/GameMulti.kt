package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class GameMulti : AppCompatActivity() {
    lateinit var question: TextView
    var userAnswer: Int = 0
    var realAnswer: Int = 0
    var userScore: Int = 0
    var userLife = 3
    lateinit var time: TextView
    lateinit var next: Button
    lateinit var life: TextView

    var timer_running: Boolean = false
    var time_left_in_milis: Long = START_TIMER_IN_MILIS


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_addition)
        val score = findViewById<TextView>(R.id.textViewScore)
        life = findViewById<TextView>(R.id.textViewLife)
        time = findViewById<TextView>(R.id.textViewTime)
        question = findViewById<TextView>(R.id.textViewQuestion)
        val answer = findViewById<EditText>(R.id.editTextAnswer)
        val ok = findViewById<Button>(R.id.buttonOK)
        next = findViewById<Button>(R.id.buttonNext)

        gameContinue()

        ok.setOnClickListener() {
            userAnswer = Integer.valueOf(answer.text.toString())

            pauseTimer()

            if (userAnswer == realAnswer) {

                userScore = userScore + 10
                question.setText("Ty bláho, dobrý, správná odpověď")
                score.setText("$userScore")
                pauseTimer()
            } else {
                userLife--
                question.setText("Jako fakt? Je to špatně")
                life.setText("$userLife")
                pauseTimer()
            }
        }

        next.setOnClickListener {
            answer.setText("")
            gameContinue()
            pauseTimer()
            resetTimer()

            if (userLife <= 0) {
                Toast.makeText(
                    applicationContext.applicationContext,
                    "Hlášení: Je tu konec hry",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, Result::class.java)
                intent.putExtra("score", userScore)
                startActivity(intent)
                finish()
            } else
                gameContinue()
        }
    }

    fun gameContinue() {
        val random = java.util.Random()
        var number1 = random.nextInt(11)
        var number2 = random.nextInt(10)
        val idButton = this.taskId
//        when(idButton == 2131231209) {
//
//        }
        realAnswer = number1 * number2

        question.setText("$number1 * $number2")
        startTimer()
    }

    val timer = object : CountDownTimer(time_left_in_milis, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            time_left_in_milis = millisUntilFinished
            updateText()
        }

        override fun onFinish() {
            timer_running = false
            pauseTimer()
            resetTimer()
            updateText()
            userLife--
            life.setText("$userLife")
            question.setText("Nerad ruším, ale čas je v háji")
        }
    }

    fun startTimer() {
        timer.start()
        timer_running = true
    }

    fun updateText() {
        val second = (time_left_in_milis / 1000) % 60
        time.setText(second.toString())
    }

    fun pauseTimer() {
        timer.cancel()
        timer_running = false
    }

    fun resetTimer() {
        time_left_in_milis = START_TIMER_IN_MILIS
        updateText()
    }

    companion object {
        private const val START_TIMER_IN_MILIS: Long = 60000
    }
}
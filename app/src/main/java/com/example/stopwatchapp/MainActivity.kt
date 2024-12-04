package com.example.stopwatchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {

    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var holdButton: Button

    private var isRunning = false
    private var timeInMilliseconds: Long = 0
    private val handler = Handler()
    private val runnable : Runnable = object : Runnable {
        override fun run() {
            timeInMilliseconds += 1000
            updateTimerText()

                handler.postDelayed(this,1000)


        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTextView = findViewById(R.id.timerTextView)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        holdButton = findViewById(R.id.holdButton)

        startButton.setOnClickListener {
            startTimer()
        }
        stopButton.setOnClickListener {
            stopTimer()
        }
        holdButton.setOnClickListener {
            holdTimer()
        }
    }

    private fun startTimer(){
        if(!isRunning){
            isRunning = true
            handler.postDelayed(runnable,1000)
        }
    }
    private fun stopTimer(){
        isRunning = true
        updateTimerText()

    }
    private fun holdTimer(){
        isRunning = false
        timeInMilliseconds = 0
        updateTimerText()
    }
    private fun updateTimerText(){
        val seconds = (timeInMilliseconds / 1000) % 60
        val minutes = (timeInMilliseconds /(1000 * 60)) % 60
        val hours = (timeInMilliseconds / (1000 * 60 * 60)) % 24

        timerTextView.text = String.format("%02d:%02d:%02d", hours,minutes,seconds)
    }
}
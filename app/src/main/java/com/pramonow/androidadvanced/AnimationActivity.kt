package com.pramonow.androidadvanced

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class AnimationActivity : AppCompatActivity() {

    private val animationHandler = Handler()
    private lateinit var animationRunnable: Runnable
    private lateinit var textViewHop: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        textViewHop = findViewById(R.id.text)

        //created the runnable for the animation text
        animationRunnable = Runnable { animationFunction() }

        //start the runnable
        animationHandler.post(animationRunnable)
    }

    private fun animationFunction() {

        val animation = textViewHop.animate().translationYBy(-60f).setDuration(200)

        animation.withEndAction {
            textViewHop.animate().translationYBy(40f).duration = 200
        }

        //do another animation
        animationHandler.postDelayed(animationRunnable, 1000)
    }
}
package com.pramonow.androidadvanced

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    lateinit var animationButton: Button
    lateinit var canvasButton: Button
    lateinit var customButton: Button
    lateinit var jniButton: Button
    lateinit var mapsButton: Button
    lateinit var recorderButton: Button
    lateinit var shakeButton: Button
    lateinit var speechTextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animationButton = findViewById(R.id.animation_button)
        canvasButton = findViewById(R.id.canvas_button)
        customButton = findViewById(R.id.custom_button)
        jniButton = findViewById(R.id.jni_button)
        mapsButton = findViewById(R.id.maps_button)
        recorderButton = findViewById(R.id.recorder_button)
        shakeButton = findViewById(R.id.shake_button)
        speechTextButton = findViewById(R.id.speech_text_button)

        animationButton.setOnClickListener { startActivity(Intent(this,AnimationActivity::class.java)) }
        canvasButton.setOnClickListener { startActivity(Intent(this,CanvasActivity::class.java)) }
        customButton.setOnClickListener { startActivity(Intent(this,CustomViewActivity::class.java)) }
        jniButton.setOnClickListener { startActivity(Intent(this,JniActivity::class.java)) }
        mapsButton.setOnClickListener { startActivity(Intent(this,MapsActivity::class.java)) }
        recorderButton.setOnClickListener { startActivity(Intent(this,RecorderActivity::class.java)) }
        shakeButton.setOnClickListener { startActivity(Intent(this,ShakeActivity::class.java)) }
        speechTextButton.setOnClickListener { startActivity(Intent(this,SpeechToTextActivity::class.java)) }


    }
}

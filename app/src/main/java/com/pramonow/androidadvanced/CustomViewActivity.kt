package com.pramonow.androidadvanced

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.pramonow.androidadvanced.customview.CustomLayoutSample

class CustomViewActivity : AppCompatActivity() {

    lateinit var customLayout:CustomLayoutSample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_view)

        customLayout = findViewById(R.id.custom_layout)
        customLayout.button.setOnClickListener { Toast.makeText(this,"CLICK",Toast.LENGTH_SHORT).show() }

    }
}
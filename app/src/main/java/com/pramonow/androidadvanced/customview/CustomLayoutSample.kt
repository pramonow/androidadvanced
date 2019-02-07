package com.pramonow.androidadvanced.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.pramonow.androidadvanced.R

class CustomLayoutSample: LinearLayout {

    lateinit var text: TextView
    lateinit var button: Button

    constructor(context: Context, attr: AttributeSet) : super(context,attr) {
        init(context,attr)
    }

    private fun init(context: Context, attr: AttributeSet)
    {
        View.inflate(context, R.layout.layout_custom,this)

        //INIT EVERY VIEW
        text = findViewById(R.id.text)
        button = findViewById(R.id.button)
    }
}
package com.pramonow.androidadvanced

import android.annotation.SuppressLint
import android.content.ClipData
import android.view.DragEvent
import android.widget.RelativeLayout
import android.view.View.OnDragListener
import android.content.ClipDescription
import android.view.View.OnLongClickListener
import android.os.Bundle
import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView

class DraggableObjectActivtiy : AppCompatActivity() {
    private lateinit var img: ImageView
    internal var msg: String? = "TAGMESSAGEOFCOURSE"
    private var layoutParams: android.widget.RelativeLayout.LayoutParams? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draggable_object)
        img = findViewById(R.id.imageView)

        img.setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View): Boolean {

                // Create a new ClipData using the tag as a label, the plain text MIME type, and
                // the already-created item. This will create a new ClipDescription object within the
                // ClipData, and set its MIME type entry to "text/plain"
                val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(img)

                v.startDrag(data, shadowBuilder, null, 0)
                //v.visibility = View.INVISIBLE
                Log.d(msg,"LONG CLICK")
                return true
            }
        })

        img.setOnClickListener{ v -> Log.d(msg,"RANDOM STUFFFFFFF")}

        img.setOnDragListener(object : View.OnDragListener {
            override fun onDrag(v: View, event: DragEvent): Boolean {

                val X = event.x
                val Y = event.y

                when (event.action) {
                    DragEvent.ACTION_DRAG_STARTED -> {
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED")
                        layoutParams = v.layoutParams as RelativeLayout.LayoutParams
                        v.visibility = View.INVISIBLE
                    }

                    DragEvent.ACTION_DRAG_ENTERED -> {
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED")
                    }

                    DragEvent.ACTION_DRAG_EXITED -> {
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED")
                        layoutParams!!.leftMargin = X.toInt()
                        layoutParams!!.topMargin = Y.toInt()
                        v.setLayoutParams(layoutParams);
                    }

                    DragEvent.ACTION_DRAG_LOCATION -> {
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION")
                    }

                    DragEvent.ACTION_DRAG_ENDED -> {
                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED")
                        v.setX(X - (v.getWidth() / 2))
                        v.setY(Y - (v.getHeight() / 2))
                        v.visibility = View.VISIBLE
                    }

                    DragEvent.ACTION_DROP -> {
                        Log.d(msg, "ACTION_DROP event")
                        v.visibility = View.VISIBLE
                    }
                }

                return true
            }
        })

    }
}
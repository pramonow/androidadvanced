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
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.widget.ImageButton


class DraggableObjectActivtiy : AppCompatActivity() {
    private lateinit var img: ImageButton
    internal var msg: String? = "TAGMESSAGEOFCOURSE"
    private var layoutParams: android.widget.RelativeLayout.LayoutParams? = null

    private var gestureDetector: GestureDetector? = null

    private inner class SingleTapConfirm : SimpleOnGestureListener() {

        override fun onSingleTapUp(event: MotionEvent): Boolean {
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draggable_object)
        img = findViewById(R.id.imageView)
        gestureDetector = GestureDetector(this, SingleTapConfirm())

        var x = 0f
        var y = 0f

        var listener = View.OnTouchListener(function = {view, motionEvent ->



            if (gestureDetector!!.onTouchEvent(motionEvent)) {
                // single tap
                Log.d(msg,"TAP")
                true
            }
            else if(motionEvent.action == MotionEvent.ACTION_DOWN) {
                x = motionEvent.getX()
                y = motionEvent.getY()
            }
            else if(motionEvent.action == MotionEvent.ACTION_MOVE){

                //Thread.sleep(300)
                var sesitivity = 20

                var x1 = motionEvent.getX()
                var y1 = motionEvent.getY()

                Log.d(msg, "MOVE " + x + " AND " + x1)

                //sensitivity
                if(Math.abs(x1+y1-x-y) > sesitivity) {

                    Log.d(msg, "SENSITIVE ENOUGH")
                    view.y = motionEvent.rawY - view.height
                    view.x = motionEvent.rawX - view.width / 2
                }

                true
            }

            false
        })

        img.setOnTouchListener(listener)
/*
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
                img.setOnTouchListener(listener)
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
        })*/

    }
}
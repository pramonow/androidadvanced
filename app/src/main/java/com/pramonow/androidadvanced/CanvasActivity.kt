package com.pramonow.androidadvanced

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView

class CanvasActivity : AppCompatActivity() {

    private var mCanvas: Canvas? = null
    private val mPaint = Paint()
    private val mPaintText = Paint(Paint.UNDERLINE_TEXT_FLAG)
    private var mBitmap: Bitmap? = null
    private val mRect = Rect()
    private val mBounds = Rect()
    private var mImageView: ImageView? = null

    private var mOffset = OFFSET

    private var mColorBackground: Int = 0
    private var mColorRectangle: Int = 0
    private var mColorAccent: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)

        mColorBackground = ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null)
        mColorRectangle = ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
        mColorAccent = ResourcesCompat.getColor(resources, R.color.colorAccent, null)

        mPaint.color = mColorBackground

        mPaintText.color = ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null)
        mPaintText.textSize = 70f

        mImageView = findViewById<View>(R.id.myimageview) as ImageView
        mImageView!!.setOnClickListener { v -> drawSomething(v) }
    }

    fun drawSomething(view: View) {
        val vWidth = view.width
        val vHeight = view.height
        val halfWidth = vWidth / 2
        val halfHeight = vHeight / 2

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888)
            mImageView!!.setImageBitmap(mBitmap)
            mCanvas = Canvas(mBitmap!!)
            mCanvas!!.drawColor(mColorBackground)
            mCanvas!!.drawText("Text", 100f, 100f, mPaintText)
            mOffset += OFFSET
        } else {
            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.color = mColorRectangle - MULTIPLIER * mOffset
                mRect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset)
                mCanvas!!.drawRect(mRect, mPaint)
                mOffset += OFFSET
            } else {
                mPaint.color = mColorAccent
                mCanvas!!.drawCircle(halfWidth.toFloat(), halfHeight.toFloat(), (halfWidth / 3).toFloat(), mPaint)
                val text = "DOne"
                mPaintText.getTextBounds(text, 0, text.length, mBounds)
                val x = halfWidth - mBounds.centerX()
                val y = halfHeight - mBounds.centerY()
                mCanvas!!.drawText(text, x.toFloat(), y.toFloat(), mPaintText)
            }
        }

        view.invalidate()
    }

    companion object {

        private val OFFSET = 120
        private val MULTIPLIER = 100
    }

}

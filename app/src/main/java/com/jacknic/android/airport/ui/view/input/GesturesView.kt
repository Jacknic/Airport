package com.jacknic.android.airport.ui.view.input

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.withTranslation

/**
 * 手势检测布局
 *
 * @author Jacknic
 */
class GesturesView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    companion object {
        private const val TAG = "GesturesView"
    }

    private val paint = Paint().apply {
        textSize = 32f
    }
    private var text = "Hello World"

    private val gestureListener = object : SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent): Boolean {
            d("onDoubleTap: ${e.x}, ${e.y}")
            return super.onDoubleTap(e)
        }

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            d("onFling: ${e1?.x}, ${e1?.y}, ${e2.x}, ${e2.y}, ${velocityX}, ${velocityY} ")
            return super.onFling(e1, e2, velocityX, velocityY)
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            d("onSingleTapUp: ")
            return super.onSingleTapUp(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            d("onSingleTapConfirmed: ")
            return super.onSingleTapConfirmed(e)
        }
    }
    private val detector = GestureDetector(context, gestureListener)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        detector.onTouchEvent(event)
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                d("Action was DOWN")
                true
            }

            MotionEvent.ACTION_MOVE -> {
                d("Action was MOVE")
                true
            }

            MotionEvent.ACTION_UP -> {
                d("Action was UP")
                true
            }

            MotionEvent.ACTION_CANCEL -> {
                d("Action was CANCEL")
                true
            }

            MotionEvent.ACTION_OUTSIDE -> {
                d("Movement occurred outside bounds of current screen element")
                true
            }

            else -> super.onTouchEvent(event)
        }
    }

    private fun d(msg: String) {
        Log.d(TAG, msg)
        text = msg
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.withTranslation(width / 2f, height / 2f) {
            val measureTextWith = paint.measureText(text)
            drawText(text, -measureTextWith / 2, 0f, paint)
        }
    }
}
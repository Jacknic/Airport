package com.jacknic.android.airport.ui.miclock

import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Camera
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.BounceInterpolator
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.math.MathUtils

/**
 * 小米时钟布局
 *
 * @author Jacknic
 */
class ClockViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayoutCompat(context, attrs) {

    companion object {
        const val MAX_ROTATE_DEGREE = 50
    }

    private var mCenterX = 0f
    private var mCenterY = 0f
    private var mCanvasRotationX = 0f
    private var mCanvasRotationY = 0f
    private var mCanvasMatrix = Matrix()
    private val mCamera = Camera()
    private var mSteadyAnimator: ValueAnimator? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = w / 2f
        mCenterY = h / 2f
    }

    override fun dispatchDraw(canvas: Canvas) {
        mCanvasMatrix.reset()

        // 使矩阵旋转
        mCamera.save()
        mCamera.rotate(-mCanvasRotationY, mCanvasRotationX, 0f)
        mCamera.getMatrix(mCanvasMatrix)
        mCamera.restore()

        mCanvasMatrix.preTranslate(-mCenterX, -mCenterY)
        mCanvasMatrix.postTranslate(mCenterX, mCenterY)

        // 画布矩阵赋值
        canvas.save()
        canvas.setMatrix(mCanvasMatrix)
        super.dispatchDraw(canvas)
        canvas.restore()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val action = event.actionMasked
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                rotationCanvasWhenMove(x, y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                rotationCanvasWhenMove(x, y)
                return true
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                startNewSteadyAnim()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 执行恢复动画
     */
    private fun startNewSteadyAnim() {
        val propertyNameRotateX = "mCanvasRotateX"
        val propertyNameRotateY = "mCanvasRotateY"
        val holderRotateX = PropertyValuesHolder.ofFloat(propertyNameRotateX, mCanvasRotationX, 0f)
        val holderRotateY = PropertyValuesHolder.ofFloat(propertyNameRotateY, mCanvasRotationY, 0f)
        val valueAnimator = ValueAnimator.ofPropertyValuesHolder(holderRotateX, holderRotateY)
        valueAnimator.duration = 1000L
        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
            override fun onAnimationUpdate(animation: ValueAnimator) {
                mCanvasRotationX = animation.getAnimatedValue(propertyNameRotateX) as Float
                mCanvasRotationY = animation.getAnimatedValue(propertyNameRotateY) as Float
                postInvalidate()
            }
        })
        mSteadyAnimator?.cancel()
        mSteadyAnimator = valueAnimator
        valueAnimator.start()
    }

    /**
     * 滑动时旋转画布
     */
    private fun rotationCanvasWhenMove(x: Float, y: Float) {
        mSteadyAnimator?.cancel()
        val dx = x - mCenterX
        val dy = y - mCenterY
        val percentX = MathUtils.clamp(dx / (width / 2), -1f, 1f)
        val percentY = MathUtils.clamp(dy / (height / 2), -1f, 1f)
        mCanvasRotationX = MAX_ROTATE_DEGREE * percentX
        mCanvasRotationY = MAX_ROTATE_DEGREE * percentY

        postInvalidate()
    }
}
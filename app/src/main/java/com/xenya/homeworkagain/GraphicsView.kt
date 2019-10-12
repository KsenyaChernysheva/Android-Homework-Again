package com.xenya.homeworkagain

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat

class GraphicsView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var innerPadding = DEF_INNER_PADDING
    private var columnWidth = DEF_W
    private val paint = Paint()

    var maxPoint = 1F
    var pointsArray = arrayOf<Int>()
        set(value) {
            val oldSize = field.size
            field = value
            maxPoint = (value.max() ?: 1).toFloat()
            invalidate()
            if (oldSize != value.size && isAttachedToWindow)
                requestLayout()
        }

    init {
        paint.color = ContextCompat.getColor(context, R.color.colorAccent)
        context.theme.obtainStyledAttributes(attrs, R.styleable.GraphicsView, 0, 0).apply {
            try {
                innerPadding =
                    getDimensionPixelSize(R.styleable.GraphicsView_innerPadding, DEF_INNER_PADDING.toInt()).toFloat()
                columnWidth =
                    getDimensionPixelSize(R.styleable.GraphicsView_columnWidth, DEF_W.toInt()).toFloat()
            } finally {
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val minw = pointsArray.size * (innerPadding + columnWidth) + paddingRight + paddingEnd
        val w = resolveSizeAndState(minw.toInt(), widthMeasureSpec, 0)

        val minh = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            MIN_H_DP,
            resources.displayMetrics
        ) + paddingTop + paddingBottom
        val h = resolveSizeAndState(minh.toInt(), heightMeasureSpec, 1)

        setMeasuredDimension(w, h)
    }

    override fun onDraw(canvas: Canvas) {
        pointsArray.forEachIndexed { index, value ->
            val left = index * (innerPadding + columnWidth) + paddingLeft
            val right = index * (innerPadding + columnWidth) + columnWidth + paddingLeft
            val top = height - ((height - paddingTop - paddingBottom) * value / maxPoint) - paddingBottom
            val bottom = height.toFloat() - paddingBottom
            canvas.drawRect(left, top, right, bottom, paint)
        }
    }

    companion object {
        private const val MIN_H_DP = 30F
        private const val DEF_W = 20F
        private const val DEF_INNER_PADDING = 0F
    }

}

package com.github.songjiubin.pageindexer.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.github.songjiubin.pageindexer.R

class PageNumberIndexer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val outlinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val pageNumPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mActiveColor: Int
    private val mInActiveColor: Int
    private var mActivePage: Int = 0
    private var thumbW: Float = 0.0f
    private var thumbH: Float = 0.0f
    private var gap: Float = 0.0f


    var mNumPages: Int = 0

    init {
        gap = 30.0f
        thumbW = 30.0f
        thumbH = thumbW * 4 / 3

        outlinePaint.style = Paint.Style.STROKE
        outlinePaint.strokeWidth = 2.0f

        pageNumPaint.style = Paint.Style.FILL
        pageNumPaint.textSize = thumbH
        pageNumPaint.textAlign = Paint.Align.CENTER

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.PageNumberIndexer, defStyleAttr, defStyleRes)
        val colorActiveId = typedArray.getResourceId(R.styleable.PageNumberIndexer_colorActive, R.color.default_page_number_indexer_color_active)
        val colorInActiveId = typedArray.getResourceId(R.styleable.PageNumberIndexer_colorInActive, R.color.default_page_number_indexer_color_inactive)
        typedArray.recycle()
        mActiveColor = ContextCompat.getColor(context, colorActiveId)
        mInActiveColor = ContextCompat.getColor(context, colorInActiveId)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)

        val height = if (heightMode == View.MeasureSpec.EXACTLY) {
            heightSize
        } else {
            val measuredHeight = thumbH * 2
            measuredHeight.toInt()
        }

        val width = if (widthMode == View.MeasureSpec.EXACTLY) {
            widthSize
        } else {
            val measuredWidth = (thumbW + gap) * mNumPages
            measuredWidth.toInt()
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        val startX = (width - mNumPages * thumbW - (mNumPages - 1) * gap) / 2
        var x = startX
        val y = (height - thumbH) / 2

        for (num in 0 until mNumPages) {
            outlinePaint.color = if (num == mActivePage) mActiveColor else mInActiveColor
            pageNumPaint.color = if (num == mActivePage) mActiveColor else mInActiveColor
            canvas?.drawRoundRect(x, y, x + thumbW, y + thumbH, 10.0f, 10.0f, outlinePaint)
            val s = "$num"
            val fontMetrics = pageNumPaint.fontMetrics
            val a = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent

            canvas?.drawText(s, x + thumbW / 2, y + thumbH / 2 + a, pageNumPaint)
            x += (gap + thumbW)
        }
    }


    fun onPageCountChanged() {
        requestLayout()
    }

    fun setActiveMarker(activePage: Int) {
        if (mActivePage != activePage) {
            mActivePage = activePage
            invalidate()

            for (listener: OnPageIndexChangeListener in onPageIndexChangeListeners) {
                listener.onPageIndexSelected(activePage)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val startX = (width - mNumPages * thumbW - mNumPages * gap) / 2
        var x = event?.x ?: 0.0f
        x -= startX
        val page = x / (thumbW + gap)
        if (page >= 0 && page <= mNumPages) {
            setActiveMarker(page.toInt())
        }
        return true
    }

    private var onPageIndexChangeListeners = arrayListOf<OnPageIndexChangeListener>()

    fun addOnPageIndexChangeListener(listener: OnPageIndexChangeListener) {
        onPageIndexChangeListeners.add(listener)
    }

    interface OnPageIndexChangeListener {
        fun onPageIndexSelected(index: Int)
    }
}
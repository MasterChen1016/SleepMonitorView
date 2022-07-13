package com.master.sleepmonitor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 睡眠监控控件
 * @author chp
 */
class SleepMonitView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private var mColumnHeight: Float = 0f //柱子的高度,根据布局高度计算
    private var mPadding: Float = 0f//左右间距
    private var mBackgroundColor: Int
    private var mPaint: Paint
    private var columnColors: ArrayList<Int> = arrayListOf()
    private var mSeptalLineHigh = 1.dp //间隔线的宽度
    private var mSeptalLineColor : Int //间隔线颜色

    var mData: MutableList<SleepEntry> = mutableListOf()
        set(value) {
            mData.clear()
            mData.addAll(value)
            invalidate()
        }


    init {
        val obt =
            context.obtainStyledAttributes(attributeSet, R.styleable.SleepMonitView)
        mSeptalLineHigh = obt.getDimension(R.styleable.SleepMonitView_septalLineHigh, 1.dp)
        columnColors.add(//浅睡柱子颜色
            obt.getColor(
                R.styleable.SleepMonitView_soberColumnColor,
                Color.parseColor("#4FF5FC")
            )
        )
        columnColors.add(//深睡柱子颜色
            obt.getColor(
                R.styleable.SleepMonitView_deepColumnColor,
                Color.parseColor("#01C8E4")
            )
        )
        columnColors.add(//清醒柱子颜色
            obt.getColor(
                R.styleable.SleepMonitView_lightColumnColor,
                Color.parseColor("#CCCDD0")
            )
        )
        mSeptalLineColor = obt.getColor(R.styleable.SleepMonitView_septalLineColor,Color.parseColor("#EDEDED"))
        mBackgroundColor = obt.getColor(R.styleable.SleepMonitView_backgroundColor, Color.WHITE)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            strokeWidth = mSeptalLineHigh
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mColumnHeight = (heightMeasureSpec - mSeptalLineHigh * 3) / 3
        mPadding = paddingLeft.toFloat()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mColumnHeight = (h - mSeptalLineHigh * 3) / 3
        mPadding = paddingLeft.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.save()
        //画背景色
        canvas.drawColor(mBackgroundColor)
        //画4条间隔线
        drawLine(canvas)
        //画柱子
        drawColumn(canvas)
        canvas.restore()
    }

    fun clear() {
        mData.clear()
        invalidate()
    }

    private fun drawColumn(canvas: Canvas) {
        var start: Int//每段的开始时长
        var end = 0//每段的结束时长
        var total = 0//总时长
        if (!mData.isNullOrEmpty()) {

            for (data in mData) {
                total += data.sleepDuration / 60 //总共睡眠时长
            }
            val perWidth = (width - 2 * mPadding) / total //每分钟的宽度
            for (data in mData) {
                start = end
                end = start + (data.sleepDuration / 60)
                when (data.type) {
                    SleepType.LIGHT -> {//浅睡
                        mPaint.color = columnColors[0]
                        canvas.drawRect(
                            perWidth * start + mPadding,
                            mSeptalLineHigh,
                            perWidth * end + mPadding,
                            mColumnHeight + mSeptalLineHigh,
                            mPaint
                        )
                    }
                    SleepType.DEEP -> {//深睡
                        mPaint.color = columnColors[1]
                        canvas.drawRect(
                            perWidth * start + mPadding,
                            mColumnHeight + mSeptalLineHigh * 2f,
                            perWidth * end + mPadding,
                            mColumnHeight * 2f + mSeptalLineHigh * 2f,
                            mPaint
                        )
                    }
                    SleepType.SOBER -> {//清醒
                        mPaint.color = columnColors[2]
                        canvas.drawRect(
                            perWidth * start + mPadding,
                            mColumnHeight * 2f + mSeptalLineHigh * 3f,
                            perWidth * end + mPadding,
                            mColumnHeight * 3f + mSeptalLineHigh * 3f,
                            mPaint
                        )
                    }
                }
            }
        }
    }

    private fun drawLine(canvas: Canvas) {
        mPaint.color = mSeptalLineColor
        canvas.drawLine(mPadding, mSeptalLineHigh, width - mPadding, mSeptalLineHigh, mPaint)
        canvas.drawLine(
            mPadding,
            mColumnHeight + mSeptalLineHigh,
            width - mPadding,
            mColumnHeight + mSeptalLineHigh,
            mPaint
        )
        canvas.drawLine(
            mPadding,
            2 * mSeptalLineHigh + 2 * mColumnHeight,
            width - mPadding,
            2 * mSeptalLineHigh + 2 * mColumnHeight,
            mPaint
        )
        canvas.drawLine(
            mPadding,
            3 * mSeptalLineHigh + 3 * mColumnHeight,
            width - mPadding,
            3 * mSeptalLineHigh + 3 * mColumnHeight,
            mPaint
        )
    }
}
package com.hxzy.calender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hx on 2017/11/13 0013.
 * email:362970502@qq.com
 * des:自定义的TextView
 */

public class CalendarTextView extends android.support.v7.widget.AppCompatTextView{
    private boolean isToady;//是否是今天
    private Paint mPaint=new Paint();//画笔

    public CalendarTextView(Context context) {
        super(context);
        initGUI(context);
    }

    public CalendarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGUI(context);
    }

    private void initGUI(Context context){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#F90E25"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isToady()){
            canvas.translate(getWidth()/2,getHeight()/2);
            canvas.drawCircle(0,0,getWidth()/2,mPaint);
        }
    }

    public CalendarTextView setToady(boolean toady) {
        isToady = toady;
        return this;
    }

    public boolean isToady() {
        return isToady;
    }
}

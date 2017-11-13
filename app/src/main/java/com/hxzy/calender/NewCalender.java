package com.hxzy.calender;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.joanzapata.iconify.widget.IconTextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hx on 2017/11/10 0010.
 * email:362970502@qq.com
 * des:自定义日历view控件
 */

public class NewCalender extends LinearLayout implements View.OnClickListener{

    private IconTextView mPrevious;//下一个月
    private IconTextView mNext;//上一个月
    private AppCompatTextView  mCalenderDate;//显示时间年月
    private RelativeLayout mCalenderHeader;//显示最上面的布局
    private GridView mCalenderWeekHeader;//包含周的布局
    private GridView mGridDay;//显示日期
    private Calendar systemCalender=Calendar.getInstance();//初始化系统控件
    private String displayFormat;//展示的日期
    private CalendarListener calendarListener;
    private String dateFormat;//显示的时间格式
    private CalendarAdapter calendarAdapter=null;

    public NewCalender(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initControl(context,attrs);
    }

    public NewCalender(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initControl(context,attrs);

    }

    private void initControl(Context context, AttributeSet attrs){
        bindViewControl(context);//绑定布局
        //获取attrs里面的配置属性
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs,R.styleable.NewCalender);
        String dateFormat=typedArray.getString(R.styleable.NewCalender_dateFormat);
        displayFormat=dateFormat;
        if (displayFormat==null){
            displayFormat="yyyy年MM月";
        }
        typedArray.recycle();//回收
        renderCalendar();
    }

    /**
     * 绑定布局
     * @param context
     */
    private void bindViewControl(Context context) {
        View view=LayoutInflater.from(context).inflate(R.layout.calender_view,this);
        mPrevious=view.findViewById(R.id.previous);
        mNext=view.findViewById(R.id.next);
        mCalenderDate=view.findViewById(R.id.text_date);
        mGridDay=view.findViewById(R.id.calender_day);
        mCalenderHeader=view.findViewById(R.id.calender_header);
        mCalenderWeekHeader=view.findViewById(R.id.calender_week_header);
        //监听事件
        mPrevious.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mCalenderDate.setOnClickListener(this);
        mCalenderWeekHeader.setAdapter(new WeekHeaderAdapter(context));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.previous://向前一个月
                systemCalender.add(Calendar.MONTH,-1);
                renderCalendar();
                break;
            case R.id.next://向后一个月
                systemCalender.add(Calendar.MONTH,+1);
                renderCalendar();
                break;
            case R.id.text_date:
                break;
            default:
                break;
        }
    }

    /**
     * 渲染
     */
    private void renderCalendar() {
        //设置头部的日期
        SimpleDateFormat dateFormat=new SimpleDateFormat(displayFormat);
        mCalenderDate.setText(dateFormat.format(systemCalender.getTime()));

        final ArrayList<Date> cells=new ArrayList<>();
        final Calendar calendar= (Calendar) systemCalender.clone();//复制
        calendar.set(calendar.DAY_OF_MONTH,1);//将给定的日历字段设置为给定值。
        int preDays=calendar.get(Calendar.DAY_OF_WEEK)-1;
        calendar.add(Calendar.DAY_OF_MONTH,-preDays);//根据日历的规则，为给定的日历字段添加或减去指定的时间量。

        int maxCells=6*7;
        while(cells.size()<maxCells){
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
        calendarAdapter=new CalendarAdapter(getContext(),cells,calendar);
        mGridDay.setAdapter(calendarAdapter);

        mGridDay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                calendarAdapter.setCheckTemp(i);
                calendarAdapter.notifyDataSetChanged();
            }
        });
        //长按
        mGridDay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                calendarAdapter.setCheckTemp(position);
                calendarAdapter.notifyDataSetChanged();
                if (calendarListener==null){
                    return false;
                }else {
                    Date date=(Date) parent.getItemAtPosition(position);
                    String dateStyles=getDateFormat();
                    SimpleDateFormat dateFormat=new SimpleDateFormat(dateStyles);
                    calendarListener.onItemClickLongListener(dateFormat.format(date).toString());
                    return true;
                }
            }
        });
    }

    public NewCalender setCalendarListener(CalendarListener calendarListener) {
        this.calendarListener = calendarListener;
        return this;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat){
        this.dateFormat = dateFormat;
    }
}

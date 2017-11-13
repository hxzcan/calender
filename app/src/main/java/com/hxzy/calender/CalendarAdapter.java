package com.hxzy.calender;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hx on 2017/11/13 0013.
 * email:362970502@qq.com
 * des:适配器
 */

public class CalendarAdapter extends ArrayAdapter<Date>{
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Date> dates;
    private Calendar calendar;
    private int[] checkList;//存放点击的状态
    private int checkPosition=-1;//被点击的item
    public CalendarAdapter(@NonNull Context context, ArrayList<Date> dates,Calendar calendar
                          ) {
        super(context, R.layout.calendar_text_day,dates);
        this.context=context;
        this.calendar=calendar;
        this.dates=dates;
        this.dates=dates;
        inflater=LayoutInflater.from(context);
        checkList=new int[dates.size()];
        for (int i=0;i<checkList.length;i++){
            checkList[i]=0;
        }
    }

    public void setCheckTemp(int checkTemp) {
        this.checkPosition = checkTemp;
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Date date = getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.calendar_text_day, parent, false);
        }
        int day = date.getDate();
        ((AppCompatTextView) convertView).setText(String.valueOf(day));

        Date nowDate = new Date();//获取当前日期
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为当月的第一天
        int maxDaysMonth = calendar.getActualMaximum(Calendar.DATE);//获取这个月的天数

        if (checkPosition==position){
            if (checkList[position]==0){
                ((CalendarTextView) convertView).setTextColor(Color.parseColor("#ffffff"));
                 convertView.setBackgroundResource(R.drawable.bg_grideview);
                checkList[position]=1;
            }
        }else {
            //如果是当月的就设置为黑色，不是当月的设置为灰色
            if (date.getMonth() == nowDate.getMonth()) {
                ((CalendarTextView) convertView).setTextColor(Color.parseColor("#000000"));
            } else {
                ((CalendarTextView) convertView).setTextColor(Color.parseColor("#666666"));
            }
            //如果是当天设置为
            if (nowDate.getYear() == date.getYear() && nowDate.getMonth() == date.getMonth() && nowDate.getDate() == day) {
                ((CalendarTextView) convertView).setToady(true).setTextColor(Color.parseColor("#F90E25"));
            }
            convertView.setBackgroundResource(R.drawable.bg_gride_view_normal);
            checkList[position]=0;
        }
        return convertView;
    }


}

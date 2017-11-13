package com.hxzy.calender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements CalendarListener{
    private ImageView mImageView;
    private NewCalender mNewCalender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNewCalender=findViewById(R.id.new_calendar);
        mNewCalender.setCalendarListener(this).setDateFormat("yyyy年MM月dd日");


        /*GlideApp.with(this)
                .load("http://p1.pstatp.com/large/166200019850062839d3" )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(mImageView);*/

    }


    @Override
    public void onItemClickLongListener(String day) {
        Toast.makeText(this, day.toString(), Toast.LENGTH_SHORT).show();
    }
}

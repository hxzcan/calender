package com.hxzy.calender;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by hx on 2017/11/13 0013.
 * email:362970502@qq.com
 * des:
 */

public class WeekHeaderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    private String[] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
    public WeekHeaderAdapter(Context context) {
        this.context=context;

        inflater=LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return weeks.length;
    }

    @Override
    public Object getItem(int i) {
        return weeks[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final LinkedHashSet<Integer> ids=new LinkedHashSet<>();

        String week= (String) getItem(position);
        if (view==null){
            view=inflater.inflate(R.layout.calendar_text_week,viewGroup,false);
        }
        ((AppCompatTextView)view).setText(week);

        return view;
    }
}

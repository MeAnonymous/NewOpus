package com.example.shivam.opus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter1 extends BaseAdapter {
    private ArrayList<RBook> mylist;
    private LayoutInflater lf = null;
    Context ctx=null;

    public MyAdapter1(Activity activity, ArrayList<RBook> mylist) {
        ctx = activity.getApplicationContext();
        this.mylist = mylist;
        lf = LayoutInflater.from(activity);
    }
    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = lf.inflate(R.layout.mycustom1, parent, false);
        }
        TextView tv=(TextView)convertView.findViewById(R.id.tv);
        TextView tv1=(TextView)convertView.findViewById(R.id.tv1);
        TextView tv2=(TextView)convertView.findViewById(R.id.tv2);
        RBook d = mylist.get(position);
        tv.setText(d.getName());
        tv1.setText(d.getInv());
        tv2.setText(d.getVar());
        return convertView;
    }
}

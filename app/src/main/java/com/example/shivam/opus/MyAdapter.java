package com.example.shivam.opus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by shiva on 27/07/2017.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Book> mylist;
    private LayoutInflater lf = null;
    Context ctx=null;

    public MyAdapter(Activity activity, ArrayList<Book> mylist) {
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
            convertView = lf.inflate(R.layout.mycustom, parent, false);
        }
        TextView tv=(TextView)convertView.findViewById(R.id.tv);
        TextView tv1=(TextView)convertView.findViewById(R.id.tv1);
        Book d = mylist.get(position);
        tv.setText(d.getName());
        tv1.setText(d.getCid());
        return convertView;
    }
}

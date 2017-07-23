package com.example.shivam.opus.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Shivam on 7/23/2017.
 */

public class OMng {
    OHelp o;
    SQLiteDatabase sb;
    Context context;
    public OMng(Context context){
        this.context=context;
        o=new OHelp(context,OCons.DB_Name,null,OCons.DB_Version);
    }
    public SQLiteDatabase open(){
        sb=o.getWritableDatabase();
        return sb;

    }
    public void close(){
        sb.close();
    }
}

package com.example.shivam.opus.dbutil;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shivam on 7/23/2017.
 */

public class OHelp extends SQLiteOpenHelper {
    Context  context;

    public OHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(OCons.LQuery);
        //Toast.makeText(context, "Login created", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.CQuery);
        //Toast.makeText(context, "Cat created", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.BQuery);
        //Toast.makeText(context, "Book created", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.MQuery);
        //Toast.makeText(context, "Member created", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.IQuery);
        //Toast.makeText(context, "Issue created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL(OCons.LQuery);
        //Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();

    }
    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery ="select "+OCons.CName+" from "+OCons.CTable;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
}

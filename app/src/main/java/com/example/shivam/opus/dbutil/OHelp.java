package com.example.shivam.opus.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
        Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.CQuery);
        Toast.makeText(context, "Cat", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.BQuery);
        Toast.makeText(context, "Book", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.MQuery);
        Toast.makeText(context, "Member", Toast.LENGTH_SHORT).show();
        db.execSQL(OCons.IQuery);
        Toast.makeText(context, "Issue", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(OCons.LQuery);
        Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();

    }
}

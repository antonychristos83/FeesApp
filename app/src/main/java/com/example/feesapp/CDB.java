package com.example.feesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class CDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="DMS";
    public CDB(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0)
    {
        arg0.execSQL("create table Login(id integer primary key autoincrement,name text,course text,uname text,pass text,payment text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion)
    {
        arg0.execSQL("DROP TABLE IF EXISTS Login");
        onCreate(arg0);
    }
    public void update(String uname, String oldpayment, String newpayment)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("payment",newpayment);
        db.update("login",values,"uname=?",new String[]{uname});
        db.close();
    }
    public  void addSignUp(String n,String c,String un,String p,String py)
    {
        try {
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv= new ContentValues();
            cv.put("name",n);
            cv.put("course",c);
            cv.put("uname",un);
            cv.put("pass",p);
            cv.put("payment",py);
            db.insert("Login",null,cv);
            db.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
            Log.d("INSERT",e.toString());
        }
    }
    public  Boolean checkusr(String uname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from Login where uname =?",new String[]{uname});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean validity(String uname,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from login where uname = ? and pass = ?",new String[]{uname,pass});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public List<CDept> getAllvalues()
    {
        List<CDept> recList= new ArrayList<CDept>();
        //Select All Query
        String selectQuery="Select * from Login";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //looping through all rows and adding to the list
        if(cursor.moveToFirst())
        {
            do {
                CDept rec= new CDept();
                rec.id=Integer.parseInt(cursor.getString(0));
                rec.name=cursor.getString(1);
                rec.course=cursor.getString(2);
                rec.usrname=cursor.getString(3);
                rec.payment=cursor.getString(5);
                recList.add(rec);
            }while (cursor.moveToNext());
        }
        return recList;
    }
    public String[] getOneDepartment(String uname)
    {
        String a[]=new String[6];
        try {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.query("Login",new String []{"name","course","payment"},"uname"+"=?",new String[]{uname},null,null,null,null);
            if(cursor!=null && cursor.getCount()!=0){
                cursor.moveToFirst();
                a[0]=cursor.getString(0);
                a[1]=cursor.getString(1);
                a[2]=cursor.getString(2);
            }
            else
            {
                a[0]="";
            }
        }catch (Exception e){
            System.out.println(e);
            Log.d("INSERT",e.toString());
        }
        return a;
    }
}
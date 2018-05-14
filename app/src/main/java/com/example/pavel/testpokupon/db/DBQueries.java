package com.example.pavel.testpokupon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pavel.testpokupon.models.OrganCard;

import java.util.ArrayList;

/**
 * Created by Pavel on 12.05.2018.
 */

public class DBQueries {
    private static DBHelper dbHelper;
    private static SQLiteDatabase database;

    private final static String SELECT_ALL = "SELECT * FROM ";
    private final static String WHERE = " WHERE ";
    private final static String EQUALS = " = ";


    public static ArrayList<OrganCard> getEntityList(Context context, String tableName){
        ArrayList<OrganCard> list = new ArrayList<>();
        isDbNull(context);

        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(SELECT_ALL + tableName, null);

        if (cursor.moveToFirst()) {
            do{
                list.add(fillItem(cursor));
            }while (cursor.moveToNext());
        }else {
            cursor.close();
            return list;
        }

        cursor.close();
        return list;
    }

    public static void addToDatabase(OrganCard card, Context context, String tableName){
        isDbNull(context);
        database = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("imagePath", card.getImagePath());
        cv.put("orgName", card.getOrganName());
        cv.put("orgLocation", card.getOrganLoc());
        cv.put("orgBlog", card.getOrganBlog());
        cv.put("reposNumb", String.valueOf(card.getRepNumb()));

        database.insert(tableName, null, cv);
        database.close();
    }


    public static int sizeOfDatabase(Context context, String tableName){
        isDbNull(context);
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(SELECT_ALL + tableName, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public static boolean isItemFromDatabase(OrganCard card, Context context, String tableName){
        isDbNull(context);
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery(SELECT_ALL + tableName + WHERE + "orgName"
                + EQUALS + "'" + card.getOrganName() + "'", null);

        if (cursor.getCount() <= 0){
            cursor.close();
            return false;
        }else {
            cursor.close();
            return true;
        }

    }


    private static OrganCard fillItem(Cursor cursor){
        String imagePath = cursor.getString(cursor.getColumnIndex("imagePath"));
        String orgName = cursor.getString(cursor.getColumnIndex("orgName"));
        String orgLocation = cursor.getString(cursor.getColumnIndex("orgLocation"));
        String orgBlog = cursor.getString(cursor.getColumnIndex("orgBlog"));
        int reposNumb = Integer.valueOf(cursor.getString(cursor.getColumnIndex("reposNumb")));

        return new OrganCard(imagePath, orgName, orgLocation, orgBlog, "", reposNumb);
    }


    private  static void isDbNull(Context context){
        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }
}
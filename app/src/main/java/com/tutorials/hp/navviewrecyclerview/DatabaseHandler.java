package com.tutorials.hp.navviewrecyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by tontus on 9/21/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "offlinePost";

    // Contacts table name
    private static final String TABLE_OFFLINEPOSTS = "offlinePosts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_POST_ID = "post_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_OFFILINEPOSTS_TABLE= "CREATE TABLE "+TABLE_OFFLINEPOSTS+ "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_POST_ID +  " INTEGER not null unique," +KEY_TITLE+" TEXT,"
                + KEY_CONTENT + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_OFFILINEPOSTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_OFFLINEPOSTS);
        onCreate(sqLiteDatabase);
    }
    public void addOffilePost(OfflinePost offlinePost) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POST_ID, offlinePost.get_post_id()); // Shop Name
        values.put(KEY_CONTENT, offlinePost.get_content()); // Shop Phone Number
        values.put(KEY_TITLE, offlinePost.get_title()); // Shop Phone Number

        // Inserting Row
        db.insert(TABLE_OFFLINEPOSTS, null, values);
        db.close(); // Closing database connection
    }

    public List<OfflinePost> getAllPosts() {
        List<OfflinePost> shopList = new ArrayList<OfflinePost>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OFFLINEPOSTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                OfflinePost offlinePost = new OfflinePost();
                offlinePost.set_id(Integer.parseInt(cursor.getString(0)));
                offlinePost.set_post_id(cursor.getInt(1));
                offlinePost.set_title(cursor.getString(2));
                offlinePost.set_content(cursor.getString(3));
                // Adding contact to list
                shopList.add(offlinePost);
            } while (cursor.moveToNext());
        }

        // return contact list
        return shopList;
    }
    public OfflinePost getPost(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_OFFLINEPOSTS, new String[]{KEY_ID,
                        KEY_POST_ID, KEY_TITLE,KEY_CONTENT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        OfflinePost post = new OfflinePost(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), cursor.getString(2),cursor.getString(3));
        // return shop
        return post;
    }
    public boolean getValidation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

//        Cursor cursor = db.query(TABLE_OFFLINEPOSTS, new String[]{KEY_ID,
//                        KEY_POST_ID, KEY_TITLE,KEY_CONTENT}, KEY_POST_ID + "=?",
//                null, ""+id, null, null, null);
        String query = "SELECT * FROM "+TABLE_OFFLINEPOSTS+" WHERE "+KEY_POST_ID+"=" + id;
        Cursor  cursor = db.rawQuery(query,null);
        if (cursor != null)
            return true;
        else
            return false;


    }
}

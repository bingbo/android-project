package com.ibingbo.service.web;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ibingbo.models.User;
import com.ibingbo.utils.Http;
import com.ibingbo.utils.SQLiteHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by zhangbingbing on 16/7/30.
 */
public class UserService {

    private SQLiteHelper helper;

    public UserService(Context context) {
        this.helper = new SQLiteHelper(context);
    }

    public boolean hasUser(User user) {

        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where name = ? and password = ?", new String[]{user.getName(), user.getPassword()});
        int count = cursor.getCount();
        return count > 0;

    }

    public boolean deleteUserById(String id) {
        int res = this.helper.getWritableDatabase().delete("user", "id = ?", new String[]{id});
        return res > 0;
    }

    public boolean addUser(User user) {
        //this.helper.getWritableDatabase().execSQL("INSERT INTO person VALUES (NULL, ?, ?, ?)", new Object[]{user.getName(), user.getPassword(),user.getEmail()});
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("password", user.getPassword());
        values.put("email", user.getEmail());
        long res = this.helper.getWritableDatabase().insert("user", null, values);
        return res > 0;
    }

    public JSONArray getUserList() {
        JSONArray array = new JSONArray();
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from user", null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            String password = c.getString(c.getColumnIndex("password"));
            String email = c.getString(c.getColumnIndex("email"));
            try {
                JSONObject object = new JSONObject();
                object.put("id", id);
                object.put("name", name);
                object.put("password", password);
                object.put("email", email);
                array.put(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return array;

    }


}

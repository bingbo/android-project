package com.example.zhangbingbing.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoreDataActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editPassword;
    private EditText editEmail;

    private String name;
    private String password;
    private String email;

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String TAG = "StoreDataActivity";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "test_file";
    private FileOutputStream outputStream;
    private FileInputStream inputStream;

    private DictionaryOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);

        editName = (EditText) findViewById(R.id.edit_name);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editEmail = (EditText) findViewById(R.id.edit_email);

        //获取preference键值数据库
        preferences = this.getSharedPreferences(PREFS_NAME, 0);
        editor = preferences.edit();

        //获取文件数据库
        try {
            outputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            inputStream =openFileInput(FILE_NAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //获取Sqlite数据库
        openHelper = new DictionaryOpenHelper(this);
    }

    public void saveDataToPreference(View view) {
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();

        editor.putString("name", name).putString("password", password).putString("email", email).commit();
    }

    public void saveDataToFile(View view) throws IOException {
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();
        String result = "name: "+name+" password: "+password+" email: "+email;
        outputStream.write(result.getBytes());
        outputStream.close();
    }

    public void saveDataToSqlite(View view) {
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();
        //获取写数据库
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //新建键值对
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("password",password);
        values.put("email",email);
        db.insert(DictionaryOpenHelper.DICTIONARY_TABLE_NAME,null,values);
    }

    public void getDataFromPreference(View view) {
        Log.i(TAG, "name from preference is: " + preferences.getString("name", "test"));
        Log.i(TAG, "password from preference is: " + preferences.getString("password", "test"));
        Log.i(TAG, "email from preference is: " + preferences.getString("email", "test"));
    }
    public void getDataFromFile(View view) throws IOException {
        byte[] result=new byte[inputStream.available()];
        inputStream.read(result);
        Log.i(TAG, "result from file is: " + new String(result));
    }
    public void getDataFromSqlite(View view) {
        //数据表列
        String[] p = new String[]{"name","password","email"};
        //获取读数据库
        SQLiteDatabase db = openHelper.getReadableDatabase();
        //查询表,返回游标
        Cursor cursor = db.query(DictionaryOpenHelper.DICTIONARY_TABLE_NAME,p,null,null,null,null,null);
        //移到第一行
        cursor.moveToFirst();
        Log.i(TAG, "name from sql is: " + cursor.getString(cursor.getColumnIndex("name")));
        Log.i(TAG, "password from sql is: " + cursor.getString(cursor.getColumnIndex("password")));
        Log.i(TAG, "email from sql is: " + cursor.getString(cursor.getColumnIndex("email")));
    }

    /**
     * sqlite数据库帮助类,内部类
     */
    public class DictionaryOpenHelper extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 2;
        //数据库名
        private static final String DATABASE_NAME = "test";
        //数据表名
        private static final String DICTIONARY_TABLE_NAME = "dictionary";
        //建表语句
        private static final String DICTIONARY_TABLE_CREATE =
                "CREATE TABLE " + DICTIONARY_TABLE_NAME + " ( name TEXT, password TEXT, email TEXT);";


        public DictionaryOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DICTIONARY_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}

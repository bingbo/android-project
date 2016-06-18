package com.example.zhangbingbing.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);

        editName = (EditText) findViewById(R.id.edit_name);
        editPassword = (EditText) findViewById(R.id.edit_password);
        editEmail = (EditText) findViewById(R.id.edit_email);

        preferences = this.getSharedPreferences(PREFS_NAME,0);
        editor = preferences.edit();

    }

    public void saveDataToPreference(View view){
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();

        editor.putString("name",name).putString("password",password).putString("email",email).commit();
    }

    public void saveDataToFile(View view){
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();
    }

    public void saveDataToSqlite(View view){
        name = editName.getText().toString();
        password = editPassword.getText().toString();
        email = editEmail.getText().toString();
    }

    public void getDataFromPreference(View view){
        Log.i(TAG,"name from preference is: "+preferences.getString("name","test"));
        Log.i(TAG,"password from preference is: "+preferences.getString("password","test"));
        Log.i(TAG,"email from preference is: "+preferences.getString("email","test"));
    }
}

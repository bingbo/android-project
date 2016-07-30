package com.ibingbo.demo;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ibingbo.service.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        listView=(ListView)this.findViewById(R.id.userlist);
        UserService userService=new UserService();
        JSONObject result = userService.getUserList();
        try {
            JSONArray data = result.getJSONArray("data");
            users = new ArrayList<String>();
            for(int i=0;i<data.length();i++){
                String name = data.getJSONObject(i).get("name").toString();
                users.add(name);

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}

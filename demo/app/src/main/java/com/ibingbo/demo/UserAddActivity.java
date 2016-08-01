package com.ibingbo.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.ibingbo.models.User;
import com.ibingbo.service.UserService;

public class UserAddActivity extends AppCompatActivity {

    private EditText nameTxt;
    private EditText pwdTxt;
    private EditText emailTxt;
    private UserAddTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);
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

        nameTxt =(EditText)this.findViewById(R.id.user_name);
        pwdTxt=(EditText)this.findViewById(R.id.user_pwd);
        emailTxt=(EditText)this.findViewById(R.id.user_email);
    }

    public void addUser(View view){
        User user=new User(nameTxt.getText().toString(),pwdTxt.getText().toString(),emailTxt.getText().toString());
        task = new UserAddTask(user);
        task.execute((Void)null);
    }

    public class UserAddTask extends AsyncTask<Void,Void,Boolean>{

        private User user;
        private UserService userService;

        public UserAddTask(User user) {
            this.user=user;
            this.userService=new UserService();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(success){
                Intent intent=new Intent(UserAddActivity.this,UserListActivity.class);
                startActivity(intent);
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return userService.addUser(this.user);
        }
    }


}

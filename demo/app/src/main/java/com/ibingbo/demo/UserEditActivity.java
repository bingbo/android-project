package com.ibingbo.demo;

import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ibingbo.models.User;
import com.ibingbo.service.UserService;

import org.json.JSONException;
import org.json.JSONObject;

public class UserEditActivity extends AppCompatActivity {

    private EditText txtname;
    private EditText txtpwd;
    private EditText txtemail;
    private UpdateUserTask updateUserTask;
    private UserService userService;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        txtemail = (EditText) this.findViewById(R.id.txt_email);
        txtname = (EditText) this.findViewById(R.id.txt_name);
        txtpwd = (EditText) this.findViewById(R.id.txt_pwd);
        userService = new UserService();

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        /*
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        userService=new UserService();
        User user = userService.getUserById(id);
        txtemail.setText(user.getEmail());
        */
        GetUserTask getUserTask=new GetUserTask(id);
        getUserTask.execute((Void)null);
    }

    public void updateUser(View view) {
        User user = new User(txtname.getText().toString(), txtpwd.getText().toString(), txtemail.getText().toString());

        user.setId(this.id);
        updateUserTask = new UpdateUserTask(user);
        updateUserTask.execute((Void) null);
    }

    public class GetUserTask extends AsyncTask<Void, Void, User> {
        private int id;

        public GetUserTask(int id) {
            this.id = id;
        }

        @Override
        protected void onPostExecute(User us) {

            txtname.setText(us.getName());
            txtpwd.setText(us.getPassword());
            txtemail.setText(us.getEmail());
        }

        @Override
        protected User doInBackground(Void... voids) {
            return userService.getUserById(this.id);
        }
    }

    public class UpdateUserTask extends AsyncTask<Void, Void, Boolean> {
        private User user;

        public UpdateUserTask(User user) {
            this.user = user;
        }

        @Override
        protected void onPostExecute(Boolean res) {
            //super.onPostExecute(aBoolean);
            Intent intent =new Intent(UserEditActivity.this,UserListActivity.class);
            if(res){
                setResult(RESULT_OK,intent);
            }else{
                setResult(RESULT_CANCELED,intent);
            }
            finish();
        }

        /*
                @Override
                protected void onPostExecute(Boolean result) {
                    Intent intent=new Intent(UserEditActivity.this,UserListActivity.class);
                    if (result) {
                        setResult(RESULT_OK,intent);
                    } else {
                        setResult(RESULT_CANCELED,intent);
                    }
                    finish();
                }
        */
        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean result = userService.updateUser(this.user);
            return result;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}

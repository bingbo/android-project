package com.ibingbo.demo;

import android.animation.Animator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ibingbo.service.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private ListView listView;
    private Button addUserBtn;
    private List<String> users;
    private View progressView;
    private View userListView;
    private UserListTask task;
    private UserDeleteTask deleteTask;
    private UserService userService;
    private UserAdapter adapter;

    private final String TAG = "USER_LIST_ACTIVITY";
    private final int REQUEST_CODE = 100;

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
        userService = new UserService();
        listView = (ListView) this.findViewById(R.id.userlist);
        progressView = this.findViewById(R.id.request_progress);
        userListView = this.findViewById(R.id.userListView);
        addUserBtn = (Button) this.findViewById(R.id.add_user_btn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserListActivity.this, UserAddActivity.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(listView);
        /*
        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                getMenuInflater().inflate(R.menu.user_list_context_menu,contextMenu);
            }
        });
        */
        /*
        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        */

        /*
        UserService userService=new UserService();
        JSONObject result = userService.getUserList();
        try {
            JSONArray data = result.getJSONArray("data");


            UserAdapter adapter=new UserAdapter(this,data);
            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

        showProgress(true);
        task = new UserListTask();
        task.execute((Void) null);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_list_context_menu, menu);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("this is back here!!!" +requestCode +"|"+resultCode);
        if(requestCode == REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"修改成功!!!",Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_menu:
                Log.i(TAG, item.getTitle().toString());
                deleteTask=new UserDeleteTask((int) info.id);
                deleteTask.execute((Void) null);
                return true;
                /*
                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                boolean result = userService.deleteUserById((int)info.id);
                if(result){
                    try {
                        adapter.setUserList(userService.getUserList().getJSONArray("data"));
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                return result;
                */
            case R.id.edit_menu:
                Log.i(TAG, item.getTitle().toString());
                Intent intent=new Intent(UserListActivity.this,UserEditActivity.class);
                intent.putExtra("id",(int)info.id);
                startActivityForResult(intent,REQUEST_CODE);
                return true;
            case R.id.about_menu:
                Log.i(TAG, item.getTitle().toString());
                return true;
            default:
                return super.onContextItemSelected(item);

        }


    }

    /**
     * 通过一个进度条等来显示数据处理的进度
     *
     * @param show
     */
    public void showProgress(final boolean show) {
        //如果版本支持,则可以添加动画
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            userListView.setVisibility(show ? View.GONE : View.VISIBLE);
            userListView.animate().setDuration(shortTime).alpha(show ? 0 : 1).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    userListView.setVisibility(show ? View.GONE : View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortTime).alpha(show ? 1 : 0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            //否则直接隐藏或显示
        } else {
            userListView.setVisibility(show ? View.GONE : View.VISIBLE);
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 自定义一个异步任务去运行相应的操作而不阻塞主UI线程
     */
    public class UserListTask extends AsyncTask<Void, Void, JSONObject> {
        /**
         * 执行所有的逻辑操作后的回调函数,可以操作UI显示相应的结果
         *
         * @param result
         */
        @Override
        protected void onPostExecute(JSONObject result) {
            //super.onPostExecute(jsonObject);
            showProgress(false);
            try {
                JSONArray data = result.getJSONArray("data");
                adapter = new UserAdapter(UserListActivity.this, data);
                listView.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 在后台执行相应的逻辑操作,如请求远程服务获取数据等
         *
         * @param voids
         * @return
         */
        @Override
        protected JSONObject doInBackground(Void... voids) {
            UserService userService = new UserService();
            JSONObject result = userService.getUserList();
            return result;
        }

        /**
         * 后台程序执行前的操作
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 异步任务取消后的操作
         */
        @Override
        protected void onCancelled() {
            task = null;
            showProgress(false);
        }

        /**
         * 异步任务构造函数
         */
        public UserListTask() {
            super();
        }
    }


    public class UserDeleteTask extends AsyncTask<Void, Void, Boolean> {
        private int userId;

        public UserDeleteTask(int id) {
            this.userId = id;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                try {
                    adapter.setUserList(userService.getUserList().getJSONArray("data"));
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean result = userService.deleteUserById(this.userId);
            return result;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}

package com.example.zhangbingbing.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    private static final String TAG = "RecyclerViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recv_view);

        recyclerView.setHasFixedSize(true);



        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        String[] data = new String[]{"aaa","bbb","ccc","ddd","eee","fff"};
        adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        //为recyclerView注册上下文菜单
        registerForContextMenu(recyclerView);
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setSelected(true);
                return true;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_context_copy:
                Log.i(TAG,item.getTitle().toString());
                return true;
            case R.id.menu_item_context_cut:
                Log.i(TAG,item.getTitle().toString());
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}

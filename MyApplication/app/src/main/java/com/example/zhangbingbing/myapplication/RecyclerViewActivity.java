package com.example.zhangbingbing.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

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

    }
}

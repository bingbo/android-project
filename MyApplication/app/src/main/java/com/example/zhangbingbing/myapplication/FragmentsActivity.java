package com.example.zhangbingbing.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

public class FragmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        if (findViewById(R.id.fragment_container) != null) {
            // 不过，如果我们要从先前的状态还原，
            // 则无需执行任何操作而应返回
            // 否则就会得到重叠的 Fragment
            if (savedInstanceState != null) {
                return;
            }

            // 创建一个要放入 Activity 布局中的新 Fragment
            Fragment1 fragment1 = new Fragment1();

            // 如果此 Activity 是通过 Intent 发出的特殊指令来启动的，
            // 请将该 Intent 的 extras 以参数形式传递给该 Fragment
            fragment1.setArguments(getIntent().getExtras());

            // 将该 Fragment 添加到“fragment_container
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1).commit();


        }


    }
}

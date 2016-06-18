package com.example.zhangbingbing.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Intent intent=this.getIntent();
        String name =intent.getStringExtra("name");
        String password=intent.getStringExtra("password");

        TextView view=(TextView)this.findViewById(R.id.dsp_message);
        view.setText("hello "+name);
/*
        TextView text=new TextView(this);
        text.setTextSize(40);
        text.setText(message);

        RelativeLayout layout=(RelativeLayout)this.findViewById(R.id.content);
        layout.addView(text);
*/
    }
}

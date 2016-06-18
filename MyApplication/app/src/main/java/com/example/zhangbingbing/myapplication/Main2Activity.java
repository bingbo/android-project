package com.example.zhangbingbing.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent = null;
    private EditText editText;
    private String data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText)findViewById(R.id.edtText);
        findViewById(R.id.startService).setOnClickListener(this);
        findViewById(R.id.stopService).setOnClickListener(this);
        findViewById(R.id.send_broadcast).setOnClickListener(this);
        findViewById(R.id.go_to_ui).setOnClickListener(this);
        findViewById(R.id.go_to_rev_view).setOnClickListener(this);
        findViewById(R.id.go_to_spinner_view).setOnClickListener(this);



//        findViewById(R.id.startService).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        findViewById(R.id.stopService).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startService:
                data = editText.getText().toString();
                intent = new Intent(Main2Activity.this,MyService.class);
                intent.putExtra("data",data);
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.send_broadcast:
                data = editText.getText().toString();
                Intent i = new Intent(Main2Activity.this,MyReceiver.class);
                i.putExtra("data",data);
                this.sendBroadcast(i);
                break;
            case R.id.go_to_ui:
                intent = new Intent(Main2Activity.this,UIActivity.class);
                startActivity(intent);
                break;
            case R.id.go_to_rev_view:
                intent = new Intent(Main2Activity.this,RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.go_to_spinner_view:
                intent = new Intent(Main2Activity.this,SpinnerActivity.class);
                startActivity(intent);
                break;
        }
    }
}

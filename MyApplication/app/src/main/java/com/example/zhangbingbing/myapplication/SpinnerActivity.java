package com.example.zhangbingbing.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = (Spinner) findViewById(R.id.spinner);
        //创建一个数组适配器,数据资源在strings.xml里配置
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
        //设置适配器显示下拉列表时的样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //为下拉列表设置数据源适配器
        spinner.setAdapter(arrayAdapter);
        //为下拉列表项添加点击选择监听器
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(this,arrayAdapter.getItem(position).toString(),Toast.LENGTH_SHORT);

        toast.show();
        System.out.println(arrayAdapter.getItem(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

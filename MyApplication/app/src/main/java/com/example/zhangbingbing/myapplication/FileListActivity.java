package com.example.zhangbingbing.myapplication;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class FileListActivity extends AppCompatActivity {

    private ListView listView;
    private String rootPath;
    private static final String TAG = "FileListActivity";
    private String[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        listView = (ListView) findViewById(R.id.file_list);
        String storeState = Environment.getExternalStorageState();
        Log.i(TAG, "store state " + storeState);
        if (storeState.equals(Environment.MEDIA_MOUNTED)) {
            //获取SD卡绝对路径
            rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            //获取用户数据绝对路径
            rootPath = Environment.getDataDirectory().getAbsolutePath();
        }
        Log.i(TAG, "root path " + rootPath);
        File file = new File(rootPath);
        file.setReadable(true);
        int index=0;
        if (file.isDirectory() && file.canRead()) {
            File[] filelist = file.getParentFile().getParentFile().getParentFile().listFiles();
            files = new String[filelist.length];
            for (File f : filelist){
                System.out.println(f.getAbsolutePath());
                System.out.println(f.getName());
                files[index++]=f.getName();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        listView.setAdapter(adapter);

    }









    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }
}

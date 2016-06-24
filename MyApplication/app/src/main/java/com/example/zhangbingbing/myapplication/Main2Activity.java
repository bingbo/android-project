package com.example.zhangbingbing.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ShareActionProvider;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent = null;
    private EditText editText;
    private String data = null;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static final String TAG = "Main2Activity";

    //根据返回的结果进行处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            if(resultCode==RESULT_OK){
                Log.i("main2activity","select contact success");
                //获取数据
                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                //查询电话号码
                Cursor cursor=getContentResolver().query(contactUri,projection,null,null,null);
                cursor.moveToFirst();
                int column =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number =cursor.getString(column);
                Log.i("main2activity",number);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = (EditText) findViewById(R.id.edtText);
        findViewById(R.id.startService).setOnClickListener(this);
        findViewById(R.id.stopService).setOnClickListener(this);
        findViewById(R.id.send_broadcast).setOnClickListener(this);
        findViewById(R.id.go_to_ui).setOnClickListener(this);
        findViewById(R.id.go_to_rev_view).setOnClickListener(this);
        findViewById(R.id.go_to_spinner_view).setOnClickListener(this);
        findViewById(R.id.go_to_save_data).setOnClickListener(this);
        findViewById(R.id.go_to_fragment_activity).setOnClickListener(this);
        findViewById(R.id.call_broswer).setOnClickListener(this);
        findViewById(R.id.call_calendar).setOnClickListener(this);
        findViewById(R.id.call_email).setOnClickListener(this);
        findViewById(R.id.call_map).setOnClickListener(this);
        findViewById(R.id.call_phone).setOnClickListener(this);
        findViewById(R.id.call_calendar).setOnClickListener(this);
        findViewById(R.id.select_contact).setOnClickListener(this);
        findViewById(R.id.list_files).setOnClickListener(this);


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
        switch (v.getId()) {
            case R.id.startService:
                data = editText.getText().toString();
                intent = new Intent(Main2Activity.this, MyService.class);
                intent.putExtra("data", data);
                startService(intent);
                break;
            case R.id.stopService:
                stopService(intent);
                break;
            case R.id.send_broadcast:
                data = editText.getText().toString();
                Intent i = new Intent(Main2Activity.this, MyReceiver.class);
                i.putExtra("data", data);
                this.sendBroadcast(i);
                break;
            case R.id.go_to_ui:
                intent = new Intent(Main2Activity.this, UIActivity.class);
                startActivity(intent);
                break;
            case R.id.go_to_rev_view:
                intent = new Intent(Main2Activity.this, RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.go_to_spinner_view:
                intent = new Intent(Main2Activity.this, SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.go_to_save_data:
                intent = new Intent(Main2Activity.this, StoreDataActivity.class);
                startActivity(intent);
                break;
            case R.id.go_to_fragment_activity:
                intent = new Intent(Main2Activity.this, FragmentsActivity.class);
                startActivity(intent);
                break;
            case R.id.call_broswer:
                Uri url = Uri.parse("http://mama.baidu.com");
                intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
                break;
            case R.id.call_calendar:
                //判断是否授权访问日历
                int allow = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CALENDAR);
                if(allow == PackageManager.PERMISSION_GRANTED) {
                    Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
                    calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, "102920000000");
                    calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, "3293900000000");
                    calendarIntent.putExtra(CalendarContract.Events.TITLE, "Ninja class");
                    calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Secret dojo");
                    startActivity(calendarIntent);
                }
                break;
            case R.id.call_email:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ibingbo@163.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "test email");
                intent.putExtra(Intent.EXTRA_TEXT, "this is a test email...");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                Intent chooser = Intent.createChooser(intent, "send");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            case R.id.call_map:
                Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                intent = new Intent(Intent.ACTION_VIEW, location);
                List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, 0);
                Log.i("mainactivity2", String.valueOf(resolveInfos.size()));
                if (resolveInfos.size() > 0) {
                    startActivity(intent);
                }
                break;
            case R.id.call_phone:
                Uri number = Uri.parse("tel:13683592712");
                intent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(intent);
                break;
            case R.id.select_contact:
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){
                        Log.i("main2","request permission");
                    }else{
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                }
                Uri cu = Uri.parse("content://contacts");
                intent = new Intent(Intent.ACTION_PICK,cu);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(intent,1);
                break;
            case R.id.list_files:
                intent=new Intent(Main2Activity.this,FileListActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 请求权限结果处理
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.i(TAG,"permission granted");
                }else{
                    Log.i(TAG,"permission deny");
                }
                return;
        }
    }

    private ShareActionProvider shareActionProvider;
    /**
     * 创建选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu,menu);
        MenuItem item = (MenuItem) findViewById(R.id.menu_item_share);
        //shareActionProvider= (ShareActionProvider) item.getActionProvider();
        return true;
    }
    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    /**
     * 选项菜单点击回调函数
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_share:
                Log.i(TAG,"you click share button");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}

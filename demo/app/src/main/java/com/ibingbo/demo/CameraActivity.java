package com.ibingbo.demo;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ibingbo.utils.MediaFileHelper;

public class CameraActivity extends AppCompatActivity {

    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE=200;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE=100;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = MediaFileHelper.getOutputMediaFileUri(MediaFileHelper.MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Image saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            }else if(resultCode==RESULT_CANCELED){

            }else {

            }
        }

        if(requestCode==CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            }else if(resultCode==RESULT_CANCELED){

            }else {

            }
        }
    }
}

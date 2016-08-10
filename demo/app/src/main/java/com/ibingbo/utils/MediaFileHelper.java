package com.ibingbo.utils;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangbingbing on 16/8/9.
 */
public class MediaFileHelper {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    public static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"demo");
        if(! mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                Log.d("Demo","failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if(type==MEDIA_TYPE_IMAGE){
            mediaFile=new File(mediaStorageDir.getPath()+File.separator+"IMG_"+timeStamp+".jpg");
        }else if(type==MEDIA_TYPE_VIDEO){
            mediaFile=new File(mediaStorageDir.getPath()+File.separator+"VID_"+timeStamp+".mp4");
        }else {
            return null;
        }
        return mediaFile;
    }
}

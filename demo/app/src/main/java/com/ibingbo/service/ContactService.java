package com.ibingbo.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhangbingbing on 16/8/7.
 */
public class ContactService {
    private Context context;
    private ContentResolver resolver;

    private static final String TAG = "DEMO_CONTACTSERVICE";

    //联系人地址
    private static final Uri CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI;
    //联系电话地址
    private static final Uri PHONES_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    //联系邮件地址
    private static final Uri EMAILS_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;


    //联系人字段
    private static final String _ID = ContactsContract.Contacts._ID;
    private static final String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
    private static final String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
    private static final String CONTACT_ID = ContactsContract.Data.CONTACT_ID;

    //联系电话邮件字段
    private static final String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
    private static final String PHONE_TYPE = ContactsContract.CommonDataKinds.Phone.TYPE;
    private static final String EMAIL_DATA = ContactsContract.CommonDataKinds.Email.DATA;
    private static final String EMAIL_TYPE = ContactsContract.CommonDataKinds.Email.TYPE;

    public ContactService(Context context) {
        this.context = context;
        this.resolver = context.getContentResolver();
    }

    public JSONArray getContactList() {
        JSONArray array = new JSONArray();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex(_ID));
            String display_name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            //Log.i(TAG, display_name);
            ArrayList<String> phones = new ArrayList<String>();
            ArrayList<String> emails = new ArrayList<String>();

            String whereClause = CONTACT_ID + "=" + _id;

            int hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(HAS_PHONE_NUMBER));
            if (hasPhoneNumber > 0) {
                Cursor pc = resolver.query(PHONES_URI, null, whereClause, null, null);
                while (pc.moveToNext()) {
                    String phone_number = pc.getString(pc.getColumnIndex(PHONE_NUMBER));
                    int type = pc.getInt(pc.getColumnIndex(PHONE_TYPE));
                    Log.i(TAG,getPhoneTypeNameById(type) + ":" + phone_number);
                    phones.add(phone_number);
                }
                pc.close();
            }

            //Log.i(TAG, "phones : " + phones);

            Cursor ec = resolver.query(EMAILS_URI, null, whereClause, null, null);
            while (ec.moveToNext()) {
                String emailData = ec.getString(ec.getColumnIndex(EMAIL_DATA));
                int typeid = ec.getInt(ec.getColumnIndex(EMAIL_TYPE));
                Log.i(TAG,getEmailTypeNameById(typeid) + ":" + emailData);
                emails.add(emailData);
            }
            ec.close();
            //Log.i(TAG, "emails : " + emails);
            JSONObject object = new JSONObject();
            try {
                object.put("id", _id);
                object.put("name", display_name);
                object.put("phone", phones.isEmpty() ? "none" : phones.get(0));
                object.put("email", emails.isEmpty() ? "none" : emails.get(0));
                array.put(object);
                //Log.i(TAG,object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        cursor.close();
        return array;

    }

    private String getPhoneTypeNameById(int typeId) {
        switch (typeId) {
            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                return "home";
            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                return "mobile";
            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                return "work";
            default:
                return "none";
        }
    }

    private String getEmailTypeNameById(int typeId) {
        switch (typeId) {
            case ContactsContract.CommonDataKinds.Email.TYPE_MOBILE:
                return "mobile";
            case ContactsContract.CommonDataKinds.Email.TYPE_HOME:
                return "home";
            case ContactsContract.CommonDataKinds.Email.TYPE_WORK:
                return "work";
            case ContactsContract.CommonDataKinds.Email.TYPE_OTHER:
                return "other";
            default:
                return "none";
        }
    }
}

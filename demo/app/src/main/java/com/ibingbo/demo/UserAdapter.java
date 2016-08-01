package com.ibingbo.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

/**
 * Created by zhangbingbing on 16/8/1.
 */
public class UserAdapter extends BaseAdapter {

    private Context context;
    private JSONArray userList;
    private LayoutInflater inflater;

    public UserAdapter(Context ctx, JSONArray users){
        context = ctx;
        userList=users;
        inflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return userList.length();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        HolderView holderView = new HolderView();

        View rowView = inflater.inflate(R.layout.user_item,null);
        holderView.userId = (TextView)rowView.findViewById(R.id.userId);
        holderView.userName = (TextView)rowView.findViewById(R.id.userName);
        holderView.userPwd = (TextView)rowView.findViewById(R.id.userPwd);
        holderView.userEmail = (TextView)rowView.findViewById(R.id.userEmail);
        try {
            holderView.userId.setText(userList.getJSONObject(position).getString("id"));
            holderView.userName.setText(userList.getJSONObject(position).getString("name"));
            holderView.userPwd.setText(userList.getJSONObject(position).getString("password"));
            holderView.userEmail.setText(userList.getJSONObject(position).getString("email"));
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Toast.makeText(context, "you click " + userList.getJSONObject(position).getString("name"), Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            return rowView;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public class HolderView{
        public TextView userId;
        public TextView userName;
        public TextView userPwd;
        public TextView userEmail;
    }
}

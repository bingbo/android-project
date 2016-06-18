package com.example.zhangbingbing.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhangbingbing on 16/6/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(TextView itemView) {
            super(itemView);
            textView = itemView;
        }
    }

    private String[] mData;
    public MyAdapter(String[] data) {
        mData = data;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter.ViewHolder(new TextView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }
}

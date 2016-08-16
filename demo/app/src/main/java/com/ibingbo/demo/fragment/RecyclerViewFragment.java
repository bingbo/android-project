package com.ibingbo.demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.ibingbo.demo.R;
import com.ibingbo.demo.adapter.RecyclerViewAdapter;

/**
 * Created by zhangbingbing on 16/8/13.
 */
public class RecyclerViewFragment extends Fragment {
    private static final String TAG=RecyclerViewFragment.class.getSimpleName();


    RecyclerView recyclerView;


    public static RecyclerViewFragment newInstance(int sectionNum){
        RecyclerViewFragment fragment=new RecyclerViewFragment();
        Bundle args=new Bundle();
        args.putInt("section_number",sectionNum);
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //recyclerview必须在activity创建后设置数据,不在能oncreateview里填充,否则不显示数据
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new RecyclerViewAdapter(new String[]{"aaaa","bbbb","cccc","dddd"}));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_recycler_view,container,false);
        recyclerView=(RecyclerView)root.findViewById(R.id.my_recycler_view);
        return root;
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    public RecyclerViewFragment(){

    }
}
